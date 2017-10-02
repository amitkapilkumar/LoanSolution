package com.app.controller;

import static org.easymock.EasyMock.expect;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.app.builder.CalculationRequestBuilder;
import com.app.builder.CalculationResponseBuilder;
import com.app.dto.CalculationRequest;
import com.app.dto.CalculationResponse;
import com.app.exception.LoanNotPossibleException;
import com.app.factory.AppControllerFactory;
import com.app.factory.RateCalculationServiceImplFactory;
import com.app.factory.RateRequestMapperImplFactory;
import com.app.mapper.RateRequestMapper;
import com.app.mapper.RateRequestMapperImpl;
import com.app.service.RateCalculationService;
import com.app.service.RateCalculationServiceImpl;

import junit.framework.Assert;

@RunWith(PowerMockRunner.class)
public class AppControllerTest {
	
	private AppController appController;	
	private RateCalculationService rateCalculationService;
	private RateRequestMapper rateRequestMapper;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setup() {
		rateCalculationService = mock(RateCalculationServiceImpl.class);
		rateRequestMapper = mock(RateRequestMapperImpl.class);
	}
	
	@Test
	@PrepareForTest({RateCalculationServiceImplFactory.class, RateRequestMapperImplFactory.class})
	public void testProcessRequest() throws FileNotFoundException, LoanNotPossibleException {
		mockStatic(RateCalculationServiceImplFactory.class);
		expect(RateCalculationServiceImplFactory.getRateCalculationService()).andReturn((RateCalculationServiceImpl) rateCalculationService);
		
		mockStatic(RateRequestMapperImplFactory.class);
		expect(RateRequestMapperImplFactory.getRateRequestMapper()).andReturn((RateRequestMapperImpl) rateRequestMapper);
		replayAll();
		
		appController = AppControllerFactory.getAppController();
		
		String amount = "1000";
		String filepath = "/homse/user/data.csv";
		CalculationRequest req = new CalculationRequestBuilder().withFilePath(filepath).withAmount(1000).build();
		CalculationResponse expectedRes = new CalculationResponseBuilder().withRequestedAmount(1000).withrRate("7.0").withMonthlyRepayment("34.00").withMonthlyTotalRepayment("1234.00").build();
		
		when(rateRequestMapper.map(filepath, amount)).thenReturn(req);
		when(rateCalculationService.processRequest(req)).thenReturn(expectedRes);
		
		CalculationResponse actualResponse = appController.processRequest(filepath, amount);
		
		verifyAll();
		verify(rateRequestMapper).map(filepath, amount);
		verify(rateCalculationService).processRequest(req);
		
		Assert.assertEquals(expectedRes, actualResponse);
	}
	
	@Test
	@PrepareForTest({RateCalculationServiceImplFactory.class, RateRequestMapperImplFactory.class})
	public void testProcessRequestThrowingLoanNotPossibleException() throws FileNotFoundException, LoanNotPossibleException {
		mockStatic(RateCalculationServiceImplFactory.class);
		expect(RateCalculationServiceImplFactory.getRateCalculationService()).andReturn((RateCalculationServiceImpl) rateCalculationService);
		
		mockStatic(RateRequestMapperImplFactory.class);
		expect(RateRequestMapperImplFactory.getRateRequestMapper()).andReturn((RateRequestMapperImpl) rateRequestMapper);
		replayAll();
		
		appController = AppControllerFactory.getAppController();
		
		String amount = "1000";
		String filepath = "/homse/user/data.csv";
		CalculationRequest req = new CalculationRequestBuilder().withFilePath(filepath).withAmount(1000).build();
		
		when(rateRequestMapper.map(filepath, amount)).thenReturn(req);
		when(rateCalculationService.processRequest(req)).thenThrow(new LoanNotPossibleException("Loan not possible"));
		
		exception.expect(LoanNotPossibleException.class);
		exception.expectMessage("Loan not possible");
		appController.processRequest(filepath, amount);
		
		verifyAll();
		verify(rateRequestMapper).map(filepath, amount);
		verify(rateCalculationService).processRequest(req);
	}

}
