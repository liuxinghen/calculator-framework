package me.puzzle.x;

import static me.puzzle.x.TestUtil.testAssertion;

import org.junit.Test;

import me.puzzle.x.caculator.DecimalCalculator;

public class CaculatorTest {

	@Test
	public void test1() {
		DecimalCalculator calculator = new DecimalCalculator(System.out);
		calculator.apply("5 2");
		testAssertion("5 2", calculator);
	}

	@Test
	public void test2() {
		DecimalCalculator calculator = new DecimalCalculator(System.out);
		calculator.apply("2 sqrt");
		testAssertion("1.4142135623", calculator);
		calculator.apply("clear 9 sqrt");
		testAssertion("3", calculator);

	}

	@Test
	public void test3() {
		DecimalCalculator calculator = new DecimalCalculator(System.out);
		calculator.apply("5 2 -");
		testAssertion("3", calculator);
		calculator.apply("3 -");
		testAssertion("0", calculator);
		calculator.apply("clear");
		testAssertion("", calculator);

	}

	@Test
	public void test4() {
		DecimalCalculator calculator = new DecimalCalculator(System.out);
		calculator.apply("5 4 3 2");
		testAssertion("5 4 3 2", calculator);
		calculator.apply("undo undo *");
		testAssertion("20", calculator);
		calculator.apply("5 *");
		testAssertion("100", calculator);
		calculator.apply("undo");
		testAssertion("20 5", calculator);
	}

	@Test
	public void test5() {
		DecimalCalculator calculator = new DecimalCalculator(System.out);
		calculator.apply("7 12 2 /");
		testAssertion("7 6", calculator);
		calculator.apply("*");
		testAssertion("42", calculator);
		calculator.apply("4 /");
		testAssertion("10.5", calculator);
	}

	@Test
	public void test6() {
		DecimalCalculator calculator = new DecimalCalculator(System.out);
		calculator.apply("1 2 3 4 5");
		testAssertion("1 2 3 4 5", calculator);
		calculator.apply("*");
		testAssertion("1 2 3 20", calculator);
		calculator.apply("clear 3 4 -");
		testAssertion("-1", calculator);
	}

	@Test
	public void test7() {
		DecimalCalculator calculator = new DecimalCalculator(System.out);
		calculator.apply("1 2 3 4 5");
		testAssertion("1 2 3 4 5", calculator);
		calculator.apply("* * * *");
		testAssertion("120", calculator);
	}

	@Test
	public void test8() {
		DecimalCalculator calculator = new DecimalCalculator(System.out);
		calculator.apply("1 2 3 * 5 + * * 6 5");
		testAssertion("11", calculator);
	}

	@Test
	public void test9() {
		DecimalCalculator calculator = new DecimalCalculator(System.out);
		calculator.apply("undo");
		testAssertion("", calculator);
	}

}
