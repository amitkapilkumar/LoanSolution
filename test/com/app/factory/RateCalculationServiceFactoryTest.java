package com.app.factory;

import org.junit.Assert;
import org.junit.Test;

import com.app.service.RateCalculationService;
import com.app.service.RateCalculationServiceImpl;

public class RateCalculationServiceFactoryTest {

	@Test
	public void testGetRateCalculationService() {
		Assert.assertNotNull(RateCalculationServiceImplFactory.getRateCalculationService());
	}

	@Test
	public void testGetRateCalculationServiceReturnInstanceOfRateCalCulationServiceImpl() {
		RateCalculationService rcs = RateCalculationServiceImplFactory.getRateCalculationService();
		
		Assert.assertTrue(rcs instanceof RateCalculationService);
		Assert.assertTrue(rcs instanceof RateCalculationServiceImpl);
	}
}