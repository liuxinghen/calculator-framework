package me.puzzle.x.caculator.factory;

import me.puzzle.x.caculator.spec.Operator;

public interface OperationFactory<T> {

	Operator<T> getOperation(String input);

}
