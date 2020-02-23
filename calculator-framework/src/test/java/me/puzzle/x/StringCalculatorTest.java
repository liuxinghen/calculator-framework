package me.puzzle.x;

import org.junit.Assert;
import org.junit.Test;

import me.puzzle.x.caculator.CommandsCalculator;
import me.puzzle.x.caculator.string.StringOperationFactory;

public class StringCalculatorTest {

	@Test
	public void testAdd() {
		CommandsCalculator<String> calculator = new CommandsCalculator<>(new StringOperationFactory(), System.out,
				String::toString);
		calculator.apply("1 2 3 +");
		String[] expect = { "1", "23" };
		Assert.assertArrayEquals(expect, calculator.getCurrentStack().toArray());
	}

	@Test
	public void testMinus() {
		CommandsCalculator<String> calculator = new CommandsCalculator<>(new StringOperationFactory(), System.out,
				String::toString);
		calculator.apply("12123 23 3 -");
		String[] expect = { "12123", "2" };
		Assert.assertArrayEquals(expect, calculator.getCurrentStack().toArray());
		calculator.apply("-");
		String[] expect2 = { "113" };
		Assert.assertArrayEquals(expect2, calculator.getCurrentStack().toArray());

	}

	@Test
	public void testAsc() {
		CommandsCalculator<String> calculator = new CommandsCalculator<>(new StringOperationFactory(), System.out,
				String::toString);
		calculator.apply("addfjda >");
		String[] expect = { "aadddfj" };
		Assert.assertArrayEquals(expect, calculator.getCurrentStack().toArray());
		calculator.apply("undo");
		String[] expect2 = { "addfjda" };
		Assert.assertArrayEquals(expect2, calculator.getCurrentStack().toArray());

	}

}
