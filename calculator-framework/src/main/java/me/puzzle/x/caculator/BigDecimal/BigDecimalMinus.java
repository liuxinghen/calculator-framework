package me.puzzle.x.caculator.BigDecimal;

import java.math.BigDecimal;

import me.puzzle.x.caculator.operations.Abstract2ArgsOperation;

public class BigDecimalMinus extends Abstract2ArgsOperation<BigDecimal> {

	@Override
	public BigDecimal apply(BigDecimal _1stArg, BigDecimal _2ndArg) {
		return _1stArg.subtract(_2ndArg);
	}

	@Override
	protected String getOperator() {
		return "-";
	}

}
