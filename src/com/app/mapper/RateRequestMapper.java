package com.app.mapper;

import com.app.dto.CalculationRequest;

public interface RateRequestMapper {
	public CalculationRequest map(String filepath, String amount);
}
