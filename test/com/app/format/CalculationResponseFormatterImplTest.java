package com.app.format;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.app.builder.CalculationResponseBuilder;
import com.app.dto.CalculationResponse;
import com.app.factory.CalculationResponseFormatterImplFactory;

public class CalculationResponseFormatterImplTest {
	
	private CalculationResponseFormatterImpl calculationResponseFormatterImpl;
	
	@Before
	public void setup() {
		calculationResponseFormatterImpl = CalculationResponseFormatterImplFactory.getCalculationResponseFormatter();
	}

	@Test
	public void testFormatResponse() {
		CalculationResponse res = new CalculationResponseBuilder().withRequestedAmount(1000).build();
		
		Assert.assertNull(res.getRate());
		Assert.assertNull(res.getMonthlyRepayment());
		Assert.assertNull(res.getMonthlyTotalRepayment());
		
		calculationResponseFormatterImpl.formatResponse(res, 0.07123, 1234);
		
		Assert.assertEquals(res.getRate(), "7.1");
		Assert.assertEquals(res.getMonthlyRepayment(), "34.28");
		Assert.assertEquals(res.getMonthlyTotalRepayment(), "1234");
	}

}
