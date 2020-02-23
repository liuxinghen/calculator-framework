package me.puzzle.x.caculator;

import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;

import me.puzzle.x.caculator.factory.OperationFactory;
import me.puzzle.x.caculator.spec.Calculator;

public class CommandsCalculator<T> implements Calculator<T> {

	private Calculator<T> calculator;

	private PrintStream out;

	private Function<T, String> objectFormatter;

	public CommandsCalculator(OperationFactory<T> operationFactory, PrintStream out,
			Function<T, String> objectFormatter) {
		this.calculator = new SingleCommandCalculator<T>(operationFactory);
		this.out = out;
		if (objectFormatter == null) {
			this.objectFormatter = Object::toString;
		} else {
			this.objectFormatter = objectFormatter;
		}
	}

	private String errorMessage = null;

	public String stackStatus(List<T> list) {
		StringBuilder stringBuilder = new StringBuilder("stack: ");
		if (list != null) {
			list.forEach(object -> stringBuilder.append(this.objectFormatter.apply(object)).append(" "));
		}
		return stringBuilder.toString();
	}

	private Map<Integer, String> split(String input) {
		Map<Integer, String> command = new LinkedHashMap<>();
		boolean space = true;
		int start = 0;
		int end = 0;
		while (start < input.length()) {
			if (end >= input.length()) {
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

	private void println(String message) {
		if (out != null) {
			out.println(message);
		}
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
			this.println(errorMessage);
		}
		this.println(stackStatus(this.getCurrentStack()));
		return errorMessage == null;
	}

	@Override
	public List<T> getCurrentStack() {
		return calculator.getCurrentStack();
	}

}
