package me.puzzle.x.caculator;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import me.puzzle.x.caculator.bigdecimal.BigDecimalOperationFactory;

public class DecimalCalculator extends CommandsCalculator<BigDecimal> {

	private static NumberFormat nf;
	static {
		nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(10);
		nf.setRoundingMode(RoundingMode.DOWN);
	}

	public DecimalCalculator(PrintStream out) {
		super(new BigDecimalOperationFactory(), out, number -> nf.format(number.doubleValue()));
	}

}
