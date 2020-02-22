package me.puzzle.x.caculator.spec;

import java.util.Iterator;

public interface Result<T> {

	Iterator<Result<T>> undo();

	T getResult();

	String getInput();

}
