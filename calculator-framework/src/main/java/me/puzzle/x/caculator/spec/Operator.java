package me.puzzle.x.caculator.spec;

import java.util.Iterator;

public interface Operator<T> {

	boolean canHandle(String input);

	Iterator<? extends Result<T>> calculate(ArgsSupplier<T> provider);

	default Iterator<? extends Result<T>> calculate(String operator, ArgsSupplier<T> provider) {
		if (canHandle(operator)) {
			return calculate(provider);
		} else {
			return null;
		}
	};

}
