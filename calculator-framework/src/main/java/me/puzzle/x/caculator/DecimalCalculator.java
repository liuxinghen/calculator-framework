package me.puzzle.x.caculator;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import me.puzzle.x.caculator.bigdecimal.BigDecimalOperationFactory;
import me.puzzle.x.caculator.factory.OperationFactory;
import me.puzzle.x.caculator.spec.Calculator;

public class DecimalCalculator implements Calculator<BigDecimal> {

	private OperationFactory<BigDecimal> operationFactory = new BigDecimalOperationFactory();
	private Calculator<BigDecimal> calculator = new StackCalculator<>(operationFactory);

	private String errorMessage = null;

	private PrintStream out;

	public DecimalCalculator(PrintStream out) {
		if (out == null) {
			out = System.out;
		}
		this.out = out;
	}

	public static String stackStatus(List<BigDecimal> list) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(10);
		nf.setRoundingMode(RoundingMode.DOWN);
		StringBuilder stringBuilder = new StringBuilder("stack: ");
		if (list != null) {
			list.forEach(number -> stringBuilder.append(nf.format(number.doubleValue())).append(" "));
		}
		return stringBuilder.toString();
	}

	private Map<Integer, String> split(String input) {
		Map<Integer, String> command = new LinkedHashMap<>();
		boolean space = true;
		int start = 0;
		int end = 0;
		while (true) {
			if (start >= input.length()) {
				break;
			} else if (end >= input.length()) {
				command.put(start, input.substring(start, end));
				break;
			} else if (space && input.charAt(start) == ' ') {
				start++;
			} else if (space && input.charAt(start) != ' ') {
				end = start;
				space = false;
			} else if (!space && input.charAt(end) != ' ') {
				end++;
			} else {
				command.put(start, input.substring(start, end));
				space = true;
				start = end;
			}
		}
		return command;
	}

	@Override
	public boolean apply(String input) {
		if (errorMessage == null && StringUtils.isNotBlank(input)) {
			Map<Integer, String> commands = this.split(input);
			for (Entry<Integer, String> indexCommand : commands.entrySet()) {
				if (!calculator.apply(indexCommand.getValue())) {
					errorMessage = String.format("operator %s (position: %d): insufficient parameters",
							indexCommand.getValue(), indexCommand.getKey() + 1);
					break;
				}
			}
		}
		if (errorMessage != null) {
			out.println(errorMessage);
		}
		out.println(stackStatus(this.getCurrentStack()));
		return errorMessage == null;
	}

	@Override
	public List<BigDecimal> getCurrentStack() {
		return calculator.getCurrentStack();
	}

}
