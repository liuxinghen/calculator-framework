package me.puzzle.x.caculator.spec;

import java.util.function.Supplier;

import me.puzzle.x.caculator.result.ClearRecord;

public interface ArgsSupplier<T> extends Supplier<Result<T>> {

	boolean canSupply(int count);

	ClearRecord<T> getClearRecord();
}
