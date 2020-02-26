package me.puzzle.x.caculator.bigdecimal;

import java.math.BigDecimal;
import java.util.List;

import me.puzzle.x.caculator.operations.AbstractNToOneOperation;

public class BigDecimalSqrt extends AbstractNToOneOperation<BigDecimal> {

	@Override
	protected int requiredArgsCount(String operator) {
		return "sqrt".equals(operator) ? 1 : -1;
	}

	@Override
	protected BigDecimal apply(String operator, List<BigDecimal> args) {
		return BigDecimal.valueOf(Math.sqrt(args.get(0).doubleValue()));
	}

}
