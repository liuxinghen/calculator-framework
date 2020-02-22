package me.puzzle.x.caculator.BigDecimal;

import java.math.BigDecimal;

import me.puzzle.x.caculator.operations.Abstract2ArgsOperation;

public class BigDecimalDivide extends Abstract2ArgsOperation<BigDecimal> {

	@Override
	public BigDecimal apply(BigDecimal _1stArg, BigDecimal _2ndArg) {
		return new BigDecimal(_1stArg.doubleValue() / _2ndArg.doubleValue());
	}

	@Override
	protected String getOperator() {
		return "/";
	}

}
