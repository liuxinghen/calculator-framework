package me.puzzle.x.caculator.bigdecimal;

import java.math.BigDecimal;
import java.util.List;

import me.puzzle.x.caculator.operations.AbstractNToOneOperation;

public class BigDecimalAvgN extends AbstractNToOneOperation<BigDecimal> {

	@Override
	protected int requiredArgsCount(String input) {
		String[] op = input.split(":");
		if (op.length != 2 || !"avg".equals(op[0])) {
			return -1;
		}
		try {
			return Integer.parseInt(op[1]);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	@Override
	protected BigDecimal apply(String operator, List<BigDecimal> args) {
		double sum = 0;
		for (BigDecimal bigDecimal : args) {
			sum += bigDecimal.doubleValue();
		}
		return BigDecimal.valueOf(sum / args.size());
	}
}
