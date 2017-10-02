package com.app.mapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.app.dto.CalculationRequest;
import com.app.factory.RateRequestMapperImplFactory;

public class RateRequestMapperImplTest {
	
	private RateRequestMapperImpl rateRequestMapperImpl;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup() {
		rateRequestMapperImpl = RateRequestMapperImplFactory.getRateRequestMapper();
	}

	@Test
	public void testMapCall() {
		String filepath = "/home/user/data.csv";
		String amount = "1000";
		
		CalculationRequest req = rateRequestMapperImpl.map(filepath, amount);
		
		Assert.assertEquals(req.getInputFile(), filepath);
		Assert.assertEquals(req.getRequestedAmount(), 1000, 0);
	}
	
	@Test
	public void testMapThrowsNumberFormatException() {
		String filepath = "/home/user/data.csv";
		String amount = "abcd";

		exception.expect(NumberFormatException.class);
		rateRequestMapperImpl.map(filepath, amount);
	}
}