package me.puzzle.x.caculator.builder;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;
import me.puzzle.x.caculator.calculator.CommandsCalculator;
import me.puzzle.x.caculator.factory.OperationFactory;
import me.puzzle.x.caculator.operations.AbstractNToOneOperation;
import me.puzzle.x.caculator.operations.ClearOperation;
import me.puzzle.x.caculator.operations.InputOperation;
import me.puzzle.x.caculator.operations.RedoOperator;
import me.puzzle.x.caculator.operations.UndoOperation;
import me.puzzle.x.caculator.spec.Calculator;
import me.puzzle.x.caculator.spec.Operator;

@Slf4j
public class CalculatorBuilder<T> {

	private List<Operator<T>> operations = new ArrayList<>();
	private OperationFactory<T> operatorFactory = () -> operations;
	private PrintStream out;
	private Function<T, String> objectFormatter = Object::toString;

	private static boolean isMethodMatch(Class<?> methodClass, Method method, Class<?> targetType) {
		if (!Modifier.isStatic(method.getModifiers()) && !targetType.isAssignableFrom(methodClass)) {
			return false;
		}
		Class<?>[] types = method.getParameterTypes();
		for (Class<?> type : types) {
			if (!targetType.isAssignableFrom(type)) {
				return false;
			}
		}
		return targetType.isAssignableFrom(method.getReturnType());
	}

	private static <T> AbstractNToOneOperation<T> buildFromMethod(Method method) {
		if (Modifier.isStatic(method.getModifiers())) {
			return new AbstractNToOneOperation<T>() {
				@Override
				protected int requiredArgsCount(String operator) {
					return method.getName().equals(operator) ? method.getParameterCount() : -1;
				}

				@SuppressWarnings("unchecked")
				@Override
				protected T apply(String operator, List<T> args) {
					try {
						return (T) method.invoke(null, args.toArray());
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						return null;
					}
				}
			};
		} else {
			return new AbstractNToOneOperation<T>() {
				@Override
				protected int requiredArgsCount(String operator) {
					return method.getName().equals(operator) ? method.getParameterCount() + 1 : -1;
				}

				@SuppressWarnings({ "unchecked" })
				@Override
				protected T apply(String operator, List<T> args) {
					try {
						return (T) method.invoke(args.get(0), args.subList(1, args.size()).toArray());
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						return null;
					}
				}
			};
		}
	}

	/**
	 * @param methodProvider
	 * @param targetType
	 * @return
	 */
	public CalculatorBuilder<T> getOperationsByReflection(Class<?> methodProvider, Class<T> targetType) {
		Method[] methods = methodProvider.getMethods();
		Stream.of(methods).filter(method -> isMethodMatch(methodProvider, method, targetType)).forEach(method -> {
			boolean staticMehod = Modifier.isStatic(method.getModifiers());
			log.info("Registered operation name [{}], argsCount [{}] via [{}] method of [{}]", method.getName(),
					staticMehod ? method.getParameterCount() : method.getParameterCount() + 1,
					staticMehod ? "static" : "instance", methodProvider.getName());
			operations.add(buildFromMethod(method));
		});
		return this;
	}

	public CalculatorBuilder<T> addOperation(Operator<T> operation) {
		this.operations.add(operation);
		return this;
	}

	public CalculatorBuilder<T> addUndoRedoOperations() {
		operations.add(new ClearOperation<T>());
		RedoOperator<T> redoOperation = new RedoOperator<>(operatorFactory);
		operations.add(new UndoOperation<T>(redoOperation));
		operations.add(redoOperation);
		return this;
	}

	public CalculatorBuilder<T> addInputOperation(Function<String, T> inputHandler) {
		InputOperation<T> inputOperation = new InputOperation<>(inputHandler);
		operations.add(inputOperation);
		return this;
	}

	public CalculatorBuilder<T> printTo(PrintStream out) {
		this.out = out;
		return this;
	}

	public CalculatorBuilder<T> formatObject(Function<T, String> objectFormatter) {
		this.objectFormatter = objectFormatter;
		return this;
	}

	public Calculator<T> buildCommandsCalculator() {
		return new CommandsCalculator<>(operatorFactory, out, objectFormatter);
	}

}
