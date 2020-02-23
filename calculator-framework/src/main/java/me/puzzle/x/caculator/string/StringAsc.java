package me.puzzle.x.caculator.string;

import java.util.Arrays;
import java.util.List;

import me.puzzle.x.caculator.operations.AbstractNToOneOperation;

public class StringAsc extends AbstractNToOneOperation<String> {

	@Override
	protected int requiredArgsCount(String operator) {
		return ">".equals(operator) ? 1 : -1;
	}

	@Override
	protected String apply(String operator, List<String> args) {
		char[] chars = args.get(0).toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}

}
