package me.puzzle.x.caculator.impl.bigdecimal;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import me.puzzle.x.caculator.calculator.CommandsCalculator;

public class BigDecimalCommandsCalculator extends CommandsCalculator<BigDecimal> {

	private static NumberFormat nf;
	static {
		nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(10);
		nf.setRoundingMode(RoundingMode.DOWN);
	}

	public BigDecimalCommandsCalculator(PrintStream out) {
		super(new BigDecimalOperationFactory(), out, number -> nf.format(number.doubleValue()));
	}

}
