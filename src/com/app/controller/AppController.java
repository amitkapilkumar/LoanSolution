package com.app.controller;

import java.io.FileNotFoundException;

import com.app.dto.CalculationRequest;
import com.app.dto.CalculationResponse;
import com.app.exception.LoanNotPossibleException;
import com.app.factory.RateCalculationServiceImplFactory;
import com.app.factory.RateRequestMapperImplFactory;
import com.app.mapper.RateRequestMapper;
import com.app.service.RateCalculationService;

public class AppController {
	
	private RateCalculationService rateCalculationService;
	private RateRequestMapper rateRequestMapper;
	
	public AppController() {
		rateCalculationService = RateCalculationServiceImplFactory.getRateCalculationService();
		rateRequestMapper = RateRequestMapperImplFactory.getRateRequestMapper();
	}
	
	public CalculationResponse processRequest(String filepath, String amount) throws FileNotFoundException, LoanNotPossibleException {
		CalculationRequest calculationRequest = rateRequestMapper.map(filepath, amount);
		return rateCalculationService.processRequest(calculationRequest);
	}
}