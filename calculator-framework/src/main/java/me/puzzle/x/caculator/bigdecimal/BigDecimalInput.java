package me.puzzle.x.caculator.bigdecimal;

import java.math.BigDecimal;
import java.util.List;

import me.puzzle.x.caculator.operations.AbstractNToOneOperation;

public class BigDecimalInput extends AbstractNToOneOperation<BigDecimal> {

	@Override
	protected Integer requiredArgsCount(String operator) {
		try {
			new BigDecimal(operator);
			return 0;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	protected BigDecimal apply(String operator, List<BigDecimal> args) {
		return new BigDecimal(operator);
	}

}
