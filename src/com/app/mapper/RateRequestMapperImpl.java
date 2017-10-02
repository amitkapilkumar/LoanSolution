package com.app.mapper;

import com.app.dto.CalculationRequest;

public class RateRequestMapperImpl implements RateRequestMapper {

	@Override
	public CalculationRequest map(String filepath, String amount) {
		CalculationRequest request = new CalculationRequest();
		request.setInputFile(filepath);
		request.setRequestedAmount(Double.parseDouble(amount));
		return request;
	}
}