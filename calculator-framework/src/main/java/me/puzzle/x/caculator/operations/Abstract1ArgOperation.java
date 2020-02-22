package me.puzzle.x.caculator.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import me.puzzle.x.caculator.result.CalculationResult;
import me.puzzle.x.caculator.spec.Result;
import me.puzzle.x.caculator.spec.ArgsSupplier;
import me.puzzle.x.caculator.spec.Operator;

public abstract class Abstract1ArgOperation<T> implements Operator<T>, Function<T, T> {

	@Override
	public Iterator<Result<T>> calculate(ArgsSupplier<T> provider) {
		if (!provider.canSupply(1)) {
			return null;
		}
		Result<T> arg = provider.get();
		List<Result<T>> history = new ArrayList<>();
		history.add(arg);
		T result = apply(arg.getResult());
		Result<T> actionResult = new CalculationResult<>(result, getOperator(), history);
		return Arrays.asList(actionResult).iterator();
	}

	@Override
	public boolean canHandle(String input) {
		return getOperator().equals(input);
	}

	protected abstract String getOperator();

}
