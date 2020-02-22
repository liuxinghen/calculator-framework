package me.puzzle.x.caculator.operations;

import java.util.Iterator;
import java.util.function.Consumer;

import me.puzzle.x.caculator.spec.ArgsSupplier;
import me.puzzle.x.caculator.spec.Operator;
import me.puzzle.x.caculator.spec.Result;

public class UndoOperation<T> implements Operator<T> {

	private Consumer<String> undoRecorder;

	public UndoOperation() {
		this.undoRecorder = null;
	}

	public UndoOperation(Consumer<String> undoRecorder) {
		this.undoRecorder = undoRecorder;
	}

	@Override
	public boolean canHandle(String input) {
		return "undo".equals(input);
	}

	@Override
	public Iterator<Result<T>> calculate(ArgsSupplier<T> provider) {
		Result<T> result;
		if (provider.canSupply(1)) {
			result = provider.get();
		} else {
			result = provider.getClearRecord();
		}
		if (result == null) {
			return null;
		}
		if (this.undoRecorder != null) {
			this.undoRecorder.accept(result.getInput());
		}
		return result.undo();
	}
}
