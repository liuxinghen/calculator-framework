package me.puzzle.x.caculator.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import me.puzzle.x.caculator.result.CalculationResult;
import me.puzzle.x.caculator.spec.ArgsSupplier;
import me.puzzle.x.caculator.spec.Operator;
import me.puzzle.x.caculator.spec.Result;

public abstract class AbstractNToOneOperation<T> implements Operator<T> {

	/**
	 * check whether the operator string could be handled by this operation. <br/>
	 * If true, return the arguments count (bigger than or equal to 0) required by
	 * this operation. <br/>
	 * Otherwise, return a negative value to represent this operation doesn't handle
	 * this operator string.
	 * 
	 * @param operator
	 * @return
	 */
	protected abstract int requiredArgsCount(String operator);

	/**
	 * the concrete calculation process that calculate a result from n arguments.
	 * Notice that the arguments should not be changed. <br/>
	 * return null object to represent the calculation fails to get a result.
	 * 
	 * @param args
	 * @return
	 */
	protected abstract T apply(String operator, List<T> args);

	@Override
	public boolean canHandle(String input) {
		return this.requiredArgsCount(input) >= 0;
	}

	@Override
	public Iterator<Result<T>> calculate(String operator, ArgsSupplier<T> provider) {
		int n = this.requiredArgsCount(operator);
		if (n < 0 || !provider.canSupply(n)) {
			return null;
		}
		LinkedList<Result<T>> args = new LinkedList<>();
		ArrayList<T> argsArray = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			Result<T> arg = provider.get();
			argsArray.add(arg.getResult());
			args.addFirst(arg);
		}
		Collections.reverse(argsArray);
		T calculatedResult = this.apply(operator, argsArray);
		if (calculatedResult != null) {
			Result<T> result = new CalculationResult<>(calculatedResult, operator, args);
			return Arrays.asList(result).iterator();
		} else {
			return null;
		}
	}

}
