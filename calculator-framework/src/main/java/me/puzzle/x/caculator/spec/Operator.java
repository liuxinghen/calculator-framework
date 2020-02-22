package me.puzzle.x.caculator.spec;

import java.util.Iterator;

public interface Operator<T> {

	boolean couldHandle(String input);

	Iterator<Result<T>> calculate(ArgsSupplier<T> provider);

}
