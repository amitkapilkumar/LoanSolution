package com.app.factory;

import org.junit.Assert;
import org.junit.Test;

import com.app.parser.FileParser;
import com.app.parser.FileParserImpl;

public class FileParserFactoryTest {

	@Test
	public void testGetFileParser() {
		Assert.assertNotNull(FileParserImplFactory.getFileParser());
	}

	@Test
	public void testGetFileParserReturnInstanceOfFileParserImpl() {
		FileParser fileParser = FileParserImplFactory.getFileParser();
		Assert.assertTrue(fileParser instanceof FileParser);
		Assert.assertTrue(fileParser instanceof FileParserImpl);
	}
}
