package com.app.builder;

import com.app.dto.CalculationResponse;

public class CalculationResponseBuilder {
	private double requestedAmount;
	private String rate;
	private String monthlyRepayment;
	private String monthlyTotalRepayment;
	
	public CalculationResponseBuilder withRequestedAmount(double requestedAmount) {
		this.requestedAmount = requestedAmount;
		return this;
	}
	
	public CalculationResponseBuilder withrRate(String rate) {
		this.rate = rate;
		return this;
	}
	
	public CalculationResponseBuilder withMonthlyRepayment(String monthlyRepayment) {
		this.monthlyRepayment = monthlyRepayment;
		return this;
	}
	
	public CalculationResponseBuilder withMonthlyTotalRepayment(String monthlyTotalRepayment) {
		this.monthlyTotalRepayment = monthlyTotalRepayment;
		return this;
	}
	
	public CalculationResponse build() {
		CalculationResponse res = new CalculationResponse();
		res.setRequestedAmount(requestedAmount);
		res.setRate(rate);
		res.setMonthlyRepayment(monthlyRepayment);
		res.setMonthlyTotalRepayment(monthlyTotalRepayment);
		return res;
	}
}
