package me.puzzle.x;

import static me.puzzle.x.TestUtil.testAssertion;

import org.junit.Test;

import me.puzzle.x.caculator.impl.bigdecimal.BigDecimalCommandsCalculator;

public class BaseTest {

	@Test
	public void testAdd() {
		BigDecimalCommandsCalculator calculator = new BigDecimalCommandsCalculator(System.out);
		calculator.apply("1 2 +");
		testAssertion("3", calculator);
	}

	@Test
	public void testMinus() {
		BigDecimalCommandsCalculator calculator = new BigDecimalCommandsCalculator(System.out);
		calculator.apply("1 2 -");
		testAssertion("-1", calculator);
	}

	@Test
	public void testMultiple() {
		BigDecimalCommandsCalculator calculator = new BigDecimalCommandsCalculator(System.out);
		calculator.apply("1.1 1.1 *");
		testAssertion("1.21", calculator);
	}

	@Test
	public void testDivide() {
		BigDecimalCommandsCalculator calculator = new BigDecimalCommandsCalculator(System.out);
		calculator.apply("1.1 1.1 /");
		testAssertion("1", calculator);
		calculator.apply("0 /");
	}

	@Test
	public void testSqrt() {
		BigDecimalCommandsCalculator calculator = new BigDecimalCommandsCalculator(System.out);
		calculator.apply("9 sqrt");
		testAssertion("3", calculator);
		calculator.apply("1.44 sqrt");
		testAssertion("3 1.2", calculator);
	}

	@Test
	public void testAvgN() {
		BigDecimalCommandsCalculator calculator = new BigDecimalCommandsCalculator(System.out);
		calculator.apply("1 2 3 4 avg:4");
		testAssertion("2.5", calculator);
		calculator.apply("undo");
		testAssertion("1 2 3 4", calculator);
		calculator.apply("redo");
		testAssertion("2.5", calculator);
	}

	@Test
	public void testUndo() {
		BigDecimalCommandsCalculator calculator = new BigDecimalCommandsCalculator(System.out);
		calculator.apply("9 8 + undo");
		testAssertion("9 8", calculator);
		calculator.apply("redo");
		testAssertion("17", calculator);
	}

	@Test
	public void testClearUndo() {
		BigDecimalCommandsCalculator calculator = new BigDecimalCommandsCalculator(System.out);
		calculator.apply("1 2 3 clear 1 clear undo undo undo");
		testAssertion("1 2 3", calculator);
	}

	@Test
	public void testClearUndoRedo() {
		BigDecimalCommandsCalculator calculator = new BigDecimalCommandsCalculator(System.out);
		calculator.apply("1 2 3 clear 1 clear undo undo undo redo redo");
		testAssertion("1", calculator);
	}

}
