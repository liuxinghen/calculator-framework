package me.puzzle.x.caculator.bigdecimal;

import java.math.BigDecimal;
import java.util.List;

import me.puzzle.x.caculator.operations.AbstractNToOneOperation;

public class BigDecimalAdd extends AbstractNToOneOperation<BigDecimal> {

	@Override
	protected Integer requiredArgsCount(String operator) {
		if ("+".equals(operator)) {
			return 2;
		}
		return null;
	}

	@Override
	protected BigDecimal apply(String operator, List<BigDecimal> args) {
		return args.get(0).add(args.get(1));
	}

}
