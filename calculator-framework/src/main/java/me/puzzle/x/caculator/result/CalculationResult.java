package me.puzzle.x.caculator.result;

import java.util.Iterator;
import java.util.List;

import me.puzzle.x.caculator.spec.Result;

public class CalculationResult<T> implements Result<T> {

	private List<Result<T>> history;
	private String operator;
	private T result;

	public CalculationResult(T result, String operator, List<Result<T>> history) {
		this.result = result;
		this.operator = operator;
		this.history = history;
	}

	@Override
	public Iterator<Result<T>> undo() {
		return history.iterator();
	}

	@Override
	public T getResult() {
		return result;
	}

	public String getInput() {
		return operator;
	}

}
