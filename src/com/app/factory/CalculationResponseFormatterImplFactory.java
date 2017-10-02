package com.app.factory;

import com.app.format.CalculationResponseFormatterImpl;

public class CalculationResponseFormatterImplFactory {
	public static CalculationResponseFormatterImpl getCalculationResponseFormatter() {
		return new CalculationResponseFormatterImpl();
	}
}
