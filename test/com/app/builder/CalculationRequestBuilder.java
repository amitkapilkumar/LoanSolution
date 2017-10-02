package com.app.builder;

import com.app.dto.CalculationRequest;

public class CalculationRequestBuilder {
	private String filepath;
	private double amount;
	
	public CalculationRequestBuilder withFilePath(String filepath) {
		this.filepath = filepath;
		return this;
	}
	
	public CalculationRequestBuilder withAmount(double amount) {
		this.amount = amount;
		return this;
	}
	
	public CalculationRequest build() {
		CalculationRequest req = new CalculationRequest();
		req.setInputFile(filepath);
		req.setRequestedAmount(amount);
		return req;
	}
}
