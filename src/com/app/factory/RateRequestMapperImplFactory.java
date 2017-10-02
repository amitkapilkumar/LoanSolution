package com.app.factory;

import com.app.mapper.RateRequestMapperImpl;

public class RateRequestMapperImplFactory {
	public static RateRequestMapperImpl getRateRequestMapper() {
		return new RateRequestMapperImpl();
	}
}
