package me.puzzle.x.caculator.BigDecimal;

import java.math.BigDecimal;

import me.puzzle.x.caculator.operations.AbstractInputOperation;

public class BigDecimalInput extends AbstractInputOperation<BigDecimal> {

	public BigDecimalInput(String input) {
		super(input);
	}

	@Override
	public BigDecimal apply(String t) {
		return new BigDecimal(t);
	}

}
