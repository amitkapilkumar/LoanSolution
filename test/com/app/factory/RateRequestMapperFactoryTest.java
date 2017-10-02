package com.app.factory;

import org.junit.Assert;
import org.junit.Test;

import com.app.mapper.RateRequestMapper;
import com.app.mapper.RateRequestMapperImpl;

public class RateRequestMapperFactoryTest {

	@Test
	public void testGetRateRequestMapper() {
		Assert.assertNotNull(RateRequestMapperImplFactory.getRateRequestMapper());
	}

	@Test
	public void testGetRateRequestMapperReturnInstanceOfRateRequestMapper() {
		RateRequestMapper rrm = RateRequestMapperImplFactory.getRateRequestMapper();
		Assert.assertTrue(rrm instanceof RateRequestMapper);
		Assert.assertTrue(rrm instanceof RateRequestMapperImpl);
	}
}
