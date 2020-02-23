package me.puzzle.x.caculator.bigdecimal;

import java.math.BigDecimal;
import java.util.List;

import me.puzzle.x.caculator.operations.AbstractNToOneOperation;

public class BigDecimalDivide extends AbstractNToOneOperation<BigDecimal> {

	@Override
	protected int requiredArgsCount(String operator) {
		return "/".equals(operator) ? 2 : -1;
	}

	@Override
	protected BigDecimal apply(String operator, List<BigDecimal> args) {
		try {
			return BigDecimal.valueOf(args.get(0).doubleValue() / args.get(1).doubleValue());
		} catch (Exception e) {
			return null;
		}
	}

}
