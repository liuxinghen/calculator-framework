package me.puzzle.x.caculator.result;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import me.puzzle.x.caculator.spec.ArgsSupplier;
import me.puzzle.x.caculator.spec.Result;

public class ClearRecord<T> implements Result<T> {

	private List<Result<T>> stackSnapshot;

	private ClearRecord(List<Result<T>> stackSnapshot) {
		this.stackSnapshot = stackSnapshot;
	}

	public static <T> ClearRecord<T> from(ArgsSupplier<T> supplier) {
		if (supplier.canSupply(1)) {
			LinkedList<Result<T>> stackSnapshot = new LinkedList<>();
			try {
				while (true) {
					stackSnapshot.addFirst(supplier.get());
				}
			} catch (Exception e) {

			}
			return new ClearRecord<>(stackSnapshot);
		} else {
			return null;
		}
	}

	@Override
	public Iterator<Result<T>> undo() {
		return stackSnapshot.iterator();
	}

	@Override
	public T getResult() {
		throw new RuntimeException("Should not get result from a ClearRecord.");
	}

	@Override
	public String getInput() {
		return "clear";
	}

}
