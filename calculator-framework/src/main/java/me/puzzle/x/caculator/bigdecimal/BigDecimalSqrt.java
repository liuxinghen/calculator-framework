package me.puzzle.x.caculator.bigdecimal;

import java.math.BigDecimal;

import me.puzzle.x.caculator.operations.Abstract1ArgOperation;

public class BigDecimalSqrt extends Abstract1ArgOperation<BigDecimal> {

	@Override
	public BigDecimal apply(BigDecimal t) {
		return new BigDecimal(Math.sqrt(t.doubleValue()));
	}

	@Override
	protected String getOperator() {
		return "sqrt";
	}

}
