package me.puzzle.x.caculator.result;

import java.util.ArrayList;
import java.util.Iterator;

import me.puzzle.x.caculator.spec.Result;

public class InputResult<T> implements Result<T> {

	private String input;

	private T value;

	public InputResult(String input, T value) {
		this.input = input;
		this.value = value;
	}

	@Override
	public Iterator<Result<T>> undo() {
		return new ArrayList<Result<T>>().iterator();
	}

	@Override
	public T getResult() {
		return this.value;
	}

	public String getInput() {
		return this.input;
	}

}
