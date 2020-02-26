package me.puzzle.x.caculator.string;

import java.util.ArrayList;
import java.util.List;

import me.puzzle.x.caculator.factory.OperationFactory;
import me.puzzle.x.caculator.operations.ClearOperation;
import me.puzzle.x.caculator.operations.RedoOperator;
import me.puzzle.x.caculator.operations.UndoOperation;
import me.puzzle.x.caculator.spec.Operator;

public class StringOperationFactory implements OperationFactory<String> {

	private List<Operator<String>> availableOperators;

	public StringOperationFactory() {
		this.availableOperators = new ArrayList<>();
		this.availableOperators.add(new StringAdd());
		this.availableOperators.add(new StringMinus());
		this.availableOperators.add(new StringAsc());
		availableOperators.add(new ClearOperation<String>());
		RedoOperator<String> redoOperation = new RedoOperator<>(this);
		availableOperators.add(new UndoOperation<String>(redoOperation));
		availableOperators.add(redoOperation);
		availableOperators.add(new StringInput());

	}

	@Override
	public List<Operator<String>> getAvailableOperators() {
		return this.availableOperators;
	}

}
