package me.puzzle.x.caculator.bigdecimal;

import java.math.BigDecimal;
import java.util.List;

import me.puzzle.x.caculator.operations.AbstractNToOneOperation;

public class BigDecimalAdd extends AbstractNToOneOperation<BigDecimal> {

	@Override
	protected int requiredArgsCount(String operator) {
		return "+".equals(operator) ? 2 : -1;
	}

	@Override
	protected BigDecimal apply(String operator, List<BigDecimal> args) {
		return args.get(0).add(args.get(1));
	}

}
