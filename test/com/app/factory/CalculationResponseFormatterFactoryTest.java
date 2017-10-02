package com.app.factory;

import org.junit.Assert;
import org.junit.Test;

import com.app.format.CalculationResponseFormatter;
import com.app.format.CalculationResponseFormatterImpl;

public class CalculationResponseFormatterFactoryTest {

	@Test
	public void testGetCalculationResponseFormatter() {
		Assert.assertNotNull(CalculationResponseFormatterImplFactory.getCalculationResponseFormatter());
	}

	@Test
	public void testGetCalculationResponseFormatterReturnsInstanceOfCalculationResponseFormatterImpl() {
		CalculationResponseFormatter crf = CalculationResponseFormatterImplFactory.getCalculationResponseFormatter();
		Assert.assertTrue(crf instanceof CalculationResponseFormatter);
		Assert.assertTrue(crf instanceof CalculationResponseFormatterImpl);
	}
}
