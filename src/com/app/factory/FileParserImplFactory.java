package com.app.factory;

import com.app.parser.FileParserImpl;

public class FileParserImplFactory {
	public static FileParserImpl getFileParser() {
		return new FileParserImpl();
	}
}
