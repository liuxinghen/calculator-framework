package me.puzzle.x.caculator.operations;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Consumer;

import me.puzzle.x.caculator.factory.OperationFactory;
import me.puzzle.x.caculator.spec.ArgsSupplier;
import me.puzzle.x.caculator.spec.Operator;
import me.puzzle.x.caculator.spec.Result;

public class RedoOperator<T> implements Operator<T>, Consumer<String> {

	private Deque<String> record = new LinkedList<>();

	private OperationFactory<T> operationFactory;

	public RedoOperator(OperationFactory<T> operationFactory) {
		this.operationFactory = operationFactory;
	}

	@Override
	public boolean canHandle(String input) {
		return "redo".equals(input);
	}

	@Override
	public Iterator<? extends Result<T>> calculate(ArgsSupplier<T> provider) {
		if (!record.isEmpty()) {
			String input = record.pop();
			return operationFactory.getOperation(input).calculate(input, provider);
		}
		return null;
	}

	@Override
	public void accept(String input) {
		record.push(input);
	}

}
