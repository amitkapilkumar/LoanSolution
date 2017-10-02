package com.app.service;

import java.io.FileNotFoundException;

import com.app.dto.CalculationRequest;
import com.app.dto.CalculationResponse;
import com.app.exception.LoanNotPossibleException;

public interface RateCalculationService {
	public CalculationResponse processRequest(CalculationRequest calculationRequest) throws FileNotFoundException, LoanNotPossibleException;
}