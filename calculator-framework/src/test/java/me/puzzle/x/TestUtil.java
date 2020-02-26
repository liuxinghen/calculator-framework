package me.puzzle.x;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import me.puzzle.x.caculator.impl.bigdecimal.BigDecimalCommandsCalculator;

public class TestUtil {

	public static void testAssertion(String expect, BigDecimalCommandsCalculator calculator) {
		List<BigDecimal> expectList = Arrays.asList(expect.split(" ")).stream().filter(s -> StringUtils.isNotBlank(s))
				.map(s -> new BigDecimal(s)).collect(Collectors.toList());
		Assert.assertEquals(calculator.stackStatus(expectList), calculator.stackStatus(calculator.getCurrentStack()));
	}
}
