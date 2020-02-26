package me.puzzle.x.caculator.string;

import java.util.List;

import me.puzzle.x.caculator.operations.AbstractNToOneOperation;

public class StringMinus extends AbstractNToOneOperation<String> {

	@Override
	protected int requiredArgsCount(String operator) {
		return "-".equals(operator) ? 2 : -1;
	}

	@Override
	protected String apply(String operator, List<String> args) {
		return args.get(0).replace(args.get(1), "");
	}

}
