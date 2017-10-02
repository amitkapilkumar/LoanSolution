package com.app.factory;

import com.app.service.RateCalculationServiceImpl;

public class RateCalculationServiceImplFactory {
	public static RateCalculationServiceImpl getRateCalculationService() {
		return new RateCalculationServiceImpl();
		
	}
}
