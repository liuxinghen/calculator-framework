package me.puzzle.x.caculator;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import me.puzzle.x.caculator.factory.OperationFactory;
import me.puzzle.x.caculator.result.ClearRecord;
import me.puzzle.x.caculator.spec.ArgsSupplier;
import me.puzzle.x.caculator.spec.Calculator;
import me.puzzle.x.caculator.spec.Operator;
import me.puzzle.x.caculator.spec.Result;

public class StackCalculator<T> implements Calculator<T> {

	private LinkedList<Result<T>> stack = new LinkedList<>();

	private LinkedList<ClearRecord<T>> clearRecord = new LinkedList<>();

	private OperationFactory<T> operationFactory;

	public StackCalculator(OperationFactory<T> operationFactory) {
		this.operationFactory = operationFactory;
	}

	@Override
	public boolean apply(String input) {
		Operator<T> op = operationFactory.getOperation(input);
		if (op == null) {
			return false;
		}
		Iterator<? extends Result<T>> results = op.calculate(input, new ArgsSupplier<T>() {
			@Override
			public Result<T> get() {
				return stack.pop();
			}

			@Override
			public boolean canSupply(int count) {
				return stack.size() >= count;
			}

			@Override
			public ClearRecord<T> getClearRecord() {
				return clearRecord.poll();
			}
		});
		if (results == null) {
			return false;
		}
		while (results.hasNext()) {
			Result<T> result = results.next();
			if (result instanceof ClearRecord) {
				clearRecord.push((ClearRecord<T>) result);
			} else {
				stack.push(result);
			}
		}
		return true;
	}

	@Override
	public List<T> getCurrentStack() {
		List<T> snapshot = stack.stream().map(Result::getResult).collect(Collectors.toList());
		Collections.reverse(snapshot);
		return snapshot;
	}

}
