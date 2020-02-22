package me.puzzle.x.caculator.bigdecimal;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import me.puzzle.x.caculator.result.CalculationResult;
import me.puzzle.x.caculator.spec.ArgsSupplier;
import me.puzzle.x.caculator.spec.Operator;
import me.puzzle.x.caculator.spec.Result;

public class BigDecimalAvgN implements Operator<BigDecimal> {

	private Integer getNFromOperator(String input) {
		String[] op = input.split(":");
		if (op.length != 2 || !"avg".equals(op[0])) {
			return null;
		}
		try {
			int n = Integer.valueOf(op[1]);
			if (n > 0) {
				return n;
			}
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public boolean canHandle(String input) {
		return getNFromOperator(input) != null;

	}

	@Override
	public Iterator<? extends Result<BigDecimal>> calculate(String operator, ArgsSupplier<BigDecimal> provider) {
		Integer n = this.getNFromOperator(operator);
		if (n == null || !provider.canSupply(n)) {
			return null;
		}
		LinkedList<Result<BigDecimal>> args = new LinkedList<>();
		double sum = 0;
		for (int i = 0; i < n; i++) {
			Result<BigDecimal> arg = provider.get();
			sum += arg.getResult().doubleValue();
			args.addFirst(arg);
		}
		sum /= n;
		return Arrays.asList(new CalculationResult<BigDecimal>(new BigDecimal(sum), operator, args)).iterator();
	}

	@Override
	public Iterator<? extends Result<BigDecimal>> calculate(ArgsSupplier<BigDecimal> provider) {
		throw new UnsupportedOperationException();
	}
}
