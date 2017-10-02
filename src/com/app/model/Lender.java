package com.app.model;

public class Lender {
	private String name;
	private double rate;
	private double availableAmount;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public double getRate() {
		return rate;
	}
	
	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getAvailableAmount() {
		return availableAmount;
	}

	public void setAvailableAmount(double availableAmount) {
		this.availableAmount = availableAmount;
	}
	
	@Override
	public String toString() {
		return "[" + name + ", " + rate + ", " + availableAmount + "]";
	}
}