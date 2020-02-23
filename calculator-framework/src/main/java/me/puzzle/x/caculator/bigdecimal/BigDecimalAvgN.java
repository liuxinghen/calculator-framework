package me.puzzle.x.caculator.bigdecimal;

import java.math.BigDecimal;
import java.util.List;

import me.puzzle.x.caculator.operations.AbstractNToOneOperation;

public class BigDecimalAvgN extends AbstractNToOneOperation<BigDecimal> {

	protected Integer requiredArgsCount(String input) {
		String[] op = input.split(":");
		if (op.length != 2 || !"avg".equals(op[0])) {
			return null;
		}
		try {
			int n = Integer.parseInt(op[1]);
			if (n > 0) {
				return n;
			}
			return null;
		} catch (NumberFormatException e) {
			return null;
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
