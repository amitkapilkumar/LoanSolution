package com.app.builder;

import com.app.model.Lender;

public class LenderBuilder {
	private String name;
	private double rate;
	private double availableAmount;
	
	public LenderBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public LenderBuilder withRate(double rate) {
		this.rate = rate;
		return this;
	}
	
	public LenderBuilder withAvailableAmount(double availableAmount) {
		this.availableAmount = availableAmount;
		return this;
	}
	
	public Lender build() {
		Lender lender = new Lender();
		lender.setAvailableAmount(availableAmount);
		lender.setName(name);
		lender.setRate(rate);
		return lender;
	}
}
