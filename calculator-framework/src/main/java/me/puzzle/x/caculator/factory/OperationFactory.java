package me.puzzle.x.caculator.factory;

import java.util.List;

import me.puzzle.x.caculator.spec.Operator;

public interface OperationFactory<T> {

	default Operator<T> getOperation(String input) {
		for (Operator<T> handler : getAvailableOperators()) {
			if (handler.canHandle(input)) {
				return handler;
			}
		}
		return null;
	}

	List<Operator<T>> getAvailableOperators();

}
