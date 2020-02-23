package me.puzzle.x.caculator.bigdecimal;

import java.math.BigDecimal;
import java.util.List;

import me.puzzle.x.caculator.operations.AbstractNToOneOperation;

public class BigDecimalMinus extends AbstractNToOneOperation<BigDecimal> {

	@Override
	protected Integer requiredArgsCount(String operator) {
		return "-".equals(operator) ? 2 : null;
	}

	@Override
	protected BigDecimal apply(String operator, List<BigDecimal> args) {
		return args.get(0).subtract(args.get(1));
	}

}
