package me.puzzle.x.caculator.operations;

import java.util.Arrays;
import java.util.Iterator;

import me.puzzle.x.caculator.result.ClearRecord;
import me.puzzle.x.caculator.spec.ArgsSupplier;
import me.puzzle.x.caculator.spec.Operator;
import me.puzzle.x.caculator.spec.Result;

public class ClearOperation<T> implements Operator<T> {

	private String operator = "clear";

	@Override
	public boolean canHandle(String input) {
		return this.operator.equals(input);
	}

	@Override
	public Iterator<Result<T>> calculate(ArgsSupplier<T> provider) {
		Result<T> result = ClearRecord.from(provider);
		if (result != null) {
			return Arrays.asList(result).iterator();
		} else {
			return null;
		}
	}
}
