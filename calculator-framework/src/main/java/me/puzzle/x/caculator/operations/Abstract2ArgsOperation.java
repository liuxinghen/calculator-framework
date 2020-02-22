package me.puzzle.x.caculator.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;

import me.puzzle.x.caculator.result.CaculationResult;
import me.puzzle.x.caculator.spec.ArgsSupplier;
import me.puzzle.x.caculator.spec.Operator;
import me.puzzle.x.caculator.spec.Result;

public abstract class Abstract2ArgsOperation<T> implements Operator<T>, BiFunction<T, T, T> {

	@Override
	public Iterator<Result<T>> calculate(ArgsSupplier<T> provider) {
		if (!provider.canSupply(2)) {
			return null;
		}
		Result<T> _2ndArg = provider.get();
		Result<T> _1stArg = provider.get();
		List<Result<T>> history = new ArrayList<>();
		history.add(_1stArg);
		history.add(_2ndArg);
		T result = apply(_1stArg.getResult(), _2ndArg.getResult());
		if (result == null) {
			return null;
		} else {
			Result<T> actionResult = new CaculationResult<>(result, getOperator(), history);
			return Arrays.asList(actionResult).iterator();
		}
	}

	@Override
	public boolean couldHandle(String input) {
		return getOperator().equals(input);
	}

	protected abstract String getOperator();

}
