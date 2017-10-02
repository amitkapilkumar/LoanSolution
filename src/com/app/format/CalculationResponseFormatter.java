package com.app.format;

import com.app.dto.CalculationResponse;

public interface CalculationResponseFormatter {
	public void formatResponse(CalculationResponse response, double rate, double totalRepayment);
}
