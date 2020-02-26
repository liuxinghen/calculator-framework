package me.puzzle.x.caculator.impl.bigdecimal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import me.puzzle.x.caculator.factory.OperationFactory;
import me.puzzle.x.caculator.operations.ClearOperation;
import me.puzzle.x.caculator.operations.InputOperation;
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
		availableOperators.add(new BigDecimalAvgN());
		availableOperators.add(new ClearOperation<BigDecimal>());
		RedoOperator<BigDecimal> redoOperation = new RedoOperator<>(this);
		availableOperators.add(new UndoOperation<BigDecimal>(redoOperation));
		availableOperators.add(redoOperation);
		availableOperators.add(new InputOperation<BigDecimal>(BigDecimal::new));
	}

	@Override
	public List<Operator<BigDecimal>> getAvailableOperators() {
		return this.availableOperators;
	}

}
