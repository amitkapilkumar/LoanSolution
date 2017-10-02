package com.app.service;

import static org.easymock.EasyMock.expect;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.app.builder.CalculationRequestBuilder;
import com.app.builder.LenderBuilder;
import com.app.dto.CalculationRequest;
import com.app.dto.CalculationResponse;
import com.app.exception.LoanNotPossibleException;
import com.app.factory.CalculationResponseFormatterImplFactory;
import com.app.factory.FileParserImplFactory;
import com.app.factory.RateCalculationServiceImplFactory;
import com.app.factory.RateRequestMapperImplFactory;
import com.app.format.CalculationResponseFormatter;
import com.app.format.CalculationResponseFormatterImpl;
import com.app.mapper.RateRequestMapperImpl;
import com.app.model.Lender;
import com.app.parser.FileParser;
import com.app.parser.FileParserImpl;

@RunWith(PowerMockRunner.class)
public class RateCalculationServiceImplTest {
	private RateCalculationServiceImpl rateCalculationServiceImpl;
	private FileParser fileParser;
	private CalculationResponseFormatter calculationResponseFormatter;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup() {
		fileParser = mock(FileParserImpl.class);
		calculationResponseFormatter = mock(CalculationResponseFormatterImpl.class);
	}
	
	@Test
	@PrepareForTest({FileParserImplFactory.class, CalculationResponseFormatterImplFactory.class})
	public void testProcessRequest() throws FileNotFoundException, LoanNotPossibleException {
		String filepath = "/home/user/data.csv";
		List<Lender> lenders = new ArrayList<Lender>() {{
			add(new LenderBuilder().withName("Bob").withRate(0.075).withAvailableAmount(640).build());
			add(new LenderBuilder().withName("Jane").withRate(0.069).withAvailableAmount(480).build());
			add(new LenderBuilder().withName("Fred").withRate(0.071).withAvailableAmount(520).build());
		}};
		mockStatic(FileParserImplFactory.class);
		expect(FileParserImplFactory.getFileParser()).andReturn((FileParserImpl) fileParser);
		
		mockStatic(CalculationResponseFormatterImplFactory.class);
		expect(CalculationResponseFormatterImplFactory.getCalculationResponseFormatter()).andReturn((CalculationResponseFormatterImpl) calculationResponseFormatter);
		replayAll();
		
		CalculationRequest req = new CalculationRequestBuilder().withFilePath(filepath).withAmount(1000.00).build();
		
		when(fileParser.parseFile(req.getInputFile())).thenReturn(lenders);
		
		rateCalculationServiceImpl = RateCalculationServiceImplFactory.getRateCalculationService();
		
		CalculationResponse actualRes = rateCalculationServiceImpl.processRequest(req);
		
		verifyAll();
		verify(fileParser).parseFile(filepath);
		
		Assert.assertNotNull(actualRes);
	}
	
	@Test
	@PrepareForTest({FileParserImplFactory.class, CalculationResponseFormatterImplFactory.class})
	public void testProcessRequestWithLoanInSufficientException() throws FileNotFoundException, LoanNotPossibleException {
		String filepath = "/home/user/data.csv";
		List<Lender> lenders = new ArrayList<Lender>() {{
			add(new LenderBuilder().withName("Bob").withRate(0.075).withAvailableAmount(640).build());
			add(new LenderBuilder().withName("Jane").withRate(0.069).withAvailableAmount(480).build());
			add(new LenderBuilder().withName("Fred").withRate(0.071).withAvailableAmount(520).build());
		}};
		mockStatic(FileParserImplFactory.class);
		expect(FileParserImplFactory.getFileParser()).andReturn((FileParserImpl) fileParser);
		
		mockStatic(CalculationResponseFormatterImplFactory.class);
		expect(CalculationResponseFormatterImplFactory.getCalculationResponseFormatter()).andReturn((CalculationResponseFormatterImpl) calculationResponseFormatter);
		replayAll();
		
		CalculationRequest req = new CalculationRequestBuilder().withFilePath(filepath).withAmount(2000.00).build();
		
		when(fileParser.parseFile(req.getInputFile())).thenReturn(lenders);
		
		rateCalculationServiceImpl = RateCalculationServiceImplFactory.getRateCalculationService();
		
		exception.expect(LoanNotPossibleException.class);
		rateCalculationServiceImpl.processRequest(req);
		
		verifyAll();
		verify(fileParser).parseFile(filepath);		
	}

}
