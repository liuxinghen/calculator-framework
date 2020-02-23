package me.puzzle.x.caculator.string;

import java.util.List;

import me.puzzle.x.caculator.operations.AbstractNToOneOperation;

public class StringInput extends AbstractNToOneOperation<String> {

	@Override
	protected int requiredArgsCount(String operator) {
		return 0;
	}

	@Override
	protected String apply(String operator, List<String> args) {
		return operator;
	}

}
