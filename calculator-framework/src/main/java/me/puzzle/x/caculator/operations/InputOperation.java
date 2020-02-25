package me.puzzle.x.caculator.operations;

import java.util.List;
import java.util.function.Function;

public class InputOperation<T> extends AbstractNToOneOperation<T> {

	private Function<String, T> converter;

	public InputOperation(Function<String, T> converter) {
		this.converter = converter;
	}

	@Override
	public int requiredArgsCount(String operator) {
		try {
			T result = converter.apply(operator);
			return result != null ? 0 : -1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public T apply(String operator, List<T> args) {
		try {
			return converter.apply(operator);
		} catch (Exception e) {
			return null;
		}
	}
}
