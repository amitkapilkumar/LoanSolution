package com.app.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.app.util.AppConstants.COMMA;

import com.app.model.Lender;

public class FileParserImpl implements FileParser {

	@Override
	public List<Lender> parseFile(String filepath) throws FileNotFoundException {
		List<Lender> lenders = new ArrayList<Lender>();
		Scanner scanner = new Scanner(new File(filepath));
		if(scanner.hasNext()) {
			scanner.nextLine(); // skip headers
		}
		while(scanner.hasNext()) {
			Lender lender = parseLender(scanner.nextLine());
			lenders.add(lender);
		}
		scanner.close();
		return lenders;
	}
	
	private Lender parseLender(String line) {
		String[] tokens = line.split(COMMA);
		Lender lender = new Lender();
		lender.setName(tokens[0]);
		lender.setRate(Double.parseDouble(tokens[1]));
		lender.setAvailableAmount(Double.parseDouble(tokens[2]));
		return lender;
	}
}
