package me.puzzle.x.caculator.spec;

import java.util.List;

public interface Calculator<T> {

	List<T> getCurrentStack();

	boolean apply(String opeartor);
}
