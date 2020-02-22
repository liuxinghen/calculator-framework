package me.puzzle.x.caculator.BigDecimal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import me.puzzle.x.caculator.factory.OperationFactory;
import me.puzzle.x.caculator.operations.ClearOperation;
import me.puzzle.x.caculator.operations.RedoOperator;
import me.puzzle.x.caculator.operations.UndoOperation;
import me.puzzle.x.caculator.spec.Operator;

public class BigDecimalOperationFactory implements OperationFactory<BigDecimal> {

	private List<Operator<BigDecimal>> availableOperators;

	public BigDecimalOperationFactory() {
		availableOperators = new ArrayList<>();
		availableOperators.add(new BigDecimalAdd());
		availableOperators.add(new BigDecimalMinus());
		availableOperators.add(new BigDecimalMultiply());
		availableOperators.add(new BigDecimalDivide());
		availableOperators.add(new BigDecimalSqrt());
		availableOperators.add(new ClearOperation<BigDecimal>());
		RedoOperator<BigDecimal> redoOperation = new RedoOperator<BigDecimal>(this);
		availableOperators.add(new UndoOperation<BigDecimal>(redoOperation));
		availableOperators.add(redoOperation);
	}

	@Override
	public Operator<BigDecimal> getOperation(String input) {
		for (Operator<BigDecimal> handler : availableOperators) {
			if (handler.couldHandle(input)) {
				return handler;
			}
		}
		Operator<BigDecimal> inputHandler = new BigDecimalInput(input);
		if (inputHandler.couldHandle(input)) {
			return inputHandler;
		}
		return null;
	}

}
