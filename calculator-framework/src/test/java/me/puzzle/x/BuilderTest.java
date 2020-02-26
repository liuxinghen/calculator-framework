package me.puzzle.x;

import java.math.BigDecimal;

import org.junit.Test;

import me.puzzle.x.caculator.builder.CalculatorBuilder;
import me.puzzle.x.caculator.spec.Calculator;

public class BuilderTest {

	@Test
	public void testBuilder() {
		CalculatorBuilder<Double> builder = new CalculatorBuilder<>();
		Calculator<Double> calculator = builder.getOperationsByReflection(Double.class, double.class)
				.addUndoRedoOperations().addInputOperation(Double::parseDouble).printTo(System.out)
				.buildCommandsCalculator();
		calculator.apply("1.2");
		calculator.apply("3.5");
		calculator.apply("sum");
	}

	@Test
	public void testBuilder2() {
		CalculatorBuilder<BigDecimal> builder = new CalculatorBuilder<>();
		Calculator<BigDecimal> calculator = builder.getOperationsByReflection(BigDecimal.class, BigDecimal.class)
				.addUndoRedoOperations().addInputOperation(BigDecimal::new).printTo(System.out)
				.buildCommandsCalculator();
		calculator.apply("1.2");
		calculator.apply("3.5");
		calculator.apply("add");
	}
}
