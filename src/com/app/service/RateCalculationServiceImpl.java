package com.app.service;

import static com.app.util.AppConstants.DURATION;
import static com.app.util.AppConstants.NOT_SUFFICIENT_MSG;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.app.dto.CalculationRequest;
import com.app.dto.CalculationResponse;
import com.app.exception.LoanNotPossibleException;
import com.app.factory.CalculationResponseFormatterImplFactory;
import com.app.factory.FileParserImplFactory;
import com.app.format.CalculationResponseFormatter;
import com.app.model.Lender;
import com.app.parser.FileParser;

public class RateCalculationServiceImpl implements RateCalculationService {
	
	private FileParser fileParser;
	private CalculationResponseFormatter calculationResponseFormatter;
	
	public RateCalculationServiceImpl() {
		fileParser = FileParserImplFactory.getFileParser();
		calculationResponseFormatter = CalculationResponseFormatterImplFactory.getCalculationResponseFormatter();
	}

	@Override
	public CalculationResponse processRequest(CalculationRequest calculationRequest) throws FileNotFoundException, LoanNotPossibleException {
		CalculationResponse calculationResponse = new CalculationResponse();
		List<Lender> lenders = fileParser.parseFile(calculationRequest.getInputFile());
		sortLenders(lenders);
		getLowestRate(calculationRequest, calculationResponse, lenders);
		return calculationResponse;
	}

	private void getLowestRate(CalculationRequest request, CalculationResponse response, List<Lender> lenders) throws LoanNotPossibleException {
		double totalRate = 0.0;
		int noOfLenders = 0;
		double amount = 0;
		
		for(Lender lender : lenders) {
			if(amount >= request.getRequestedAmount()) {
				break;
			}
			amount += lender.getAvailableAmount();
			totalRate += lender.getRate();
			noOfLenders++;
		}
		
		if(amount < request.getRequestedAmount()) {
			throw new LoanNotPossibleException(NOT_SUFFICIENT_MSG);
		}
		
		double rate = totalRate / noOfLenders;
		double totalRepayment = request.getRequestedAmount() * (Math.pow(1.0+rate/12, 12 * DURATION));
		response.setRequestedAmount(request.getRequestedAmount());
		calculationResponseFormatter.formatResponse(response, rate, totalRepayment);
	}

	private void sortLenders(List<Lender> lenders) {
		Collections.sort(lenders, new Comparator<Lender>() {
			@Override
			public int compare(Lender o1, Lender o2) {
				if(o1.getRate() > o2.getRate())
					return 1;
				if(o1.getRate() < o2.getRate())
					return -1;
				return 0;
			}
		});
	}

}
