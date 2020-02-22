package me.puzzle.x.caculator.spec;

import java.util.List;
import java.util.function.Function;

public interface Calculator<T> extends Function<String, Boolean> {

	List<T> getCurrentStack();

}
