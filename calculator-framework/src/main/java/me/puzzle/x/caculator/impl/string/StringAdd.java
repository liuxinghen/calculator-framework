package me.puzzle.x.caculator.impl.string;

import java.util.List;

import me.puzzle.x.caculator.operations.AbstractNToOneOperation;

public class StringAdd extends AbstractNToOneOperation<String> {

	@Override
	protected int requiredArgsCount(String operator) {
		return "+".equals(operator) ? 2 : -1;
	}

	@Override
	protected String apply(String operator, List<String> args) {
		return args.get(0).concat(args.get(1));
	}

}
