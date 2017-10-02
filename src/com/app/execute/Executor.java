package com.app.execute;

import java.io.FileNotFoundException;

import com.app.dto.CalculationResponse;
import com.app.exception.LoanNotPossibleException;
import com.app.factory.AppControllerFactory;

public class Executor {
	public static void main(String[] args) throws FileNotFoundException {
		try {
			CalculationResponse calculationResponse = AppControllerFactory.getAppController().processRequest(args[0], args[1]);
			System.out.println("Requested Amount : " + calculationResponse.getRequestedAmount());
			System.out.println("Rate : " + calculationResponse.getRate());
			System.out.println("Monthly Repayment : " + calculationResponse.getMonthlyRepayment());
			System.out.println("Total Repayment : " + calculationResponse.getMonthlyTotalRepayment());
		}
		catch(LoanNotPossibleException e) {
			e.printStackTrace();
		}
	}
}