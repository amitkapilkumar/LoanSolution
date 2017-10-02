package com.app.parser;

import java.io.FileNotFoundException;
import java.util.List;

import com.app.model.Lender;

public interface FileParser {
	public List<Lender> parseFile(String filepath) throws FileNotFoundException; 
}
