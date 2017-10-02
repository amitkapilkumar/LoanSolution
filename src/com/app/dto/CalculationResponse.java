package com.app.dto;

public class CalculationResponse {
	private double requestedAmount;
	private String rate;
	private String monthlyRepayment;
	private String monthlyTotalRepayment;
	
	public double getRequestedAmount() {
		return requestedAmount;
	}
	public void setRequestedAmount(double requestedAmount) {
		this.requestedAmount = requestedAmount;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getMonthlyRepayment() {
		return monthlyRepayment;
	}
	public void setMonthlyRepayment(String monthlyRepayment) {
		this.monthlyRepayment = monthlyRepayment;
	}
	public String getMonthlyTotalRepayment() {
		return monthlyTotalRepayment;
	}
	public void setMonthlyTotalRepayment(String monthlyTotalRepayment) {
		this.monthlyTotalRepayment = monthlyTotalRepayment;
	}
}