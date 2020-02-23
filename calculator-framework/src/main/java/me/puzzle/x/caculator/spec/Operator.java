package me.puzzle.x.caculator.spec;

import java.util.Iterator;

public interface Operator<T> {

	boolean canHandle(String input);

	Iterator<Result<T>> calculate(String operator, ArgsSupplier<T> provider);

}
