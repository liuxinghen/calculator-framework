package me.puzzle.x.caculator.operations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;

import me.puzzle.x.caculator.result.InputResult;
import me.puzzle.x.caculator.spec.Result;
import me.puzzle.x.caculator.spec.ArgsSupplier;
import me.puzzle.x.caculator.spec.Operator;

public abstract class AbstractInputOperation<T> implements Operator<T>, Function<String, T> {

	private String input;

	public AbstractInputOperation(String input) {
		this.input = input;
	}

	@Override
	public Iterator<Result<T>> calculate(ArgsSupplier<T> provider) {
		T value = apply(this.input);
		Result<T> result = new InputResult<>(input, value);
		return Arrays.asList(result).iterator();
	}

	@Override
	public boolean canHandle(String input) {
		try {
			return apply(input) != null;
		} catch (Exception e) {
			return false;
		}
	}

}
