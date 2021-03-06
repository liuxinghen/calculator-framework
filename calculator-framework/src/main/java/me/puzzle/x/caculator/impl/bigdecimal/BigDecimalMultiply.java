package me.puzzle.x.caculator.impl.bigdecimal;

import java.math.BigDecimal;
import java.util.List;

import me.puzzle.x.caculator.operations.AbstractNToOneOperation;

public class BigDecimalMultiply extends AbstractNToOneOperation<BigDecimal> {

	@Override
	protected int requiredArgsCount(String operator) {
		return "*".equals(operator) ? 2 : -1;
	}

	@Override
	protected BigDecimal apply(String operator, List<BigDecimal> args) {
		return args.get(0).multiply(args.get(1));
	}

}
