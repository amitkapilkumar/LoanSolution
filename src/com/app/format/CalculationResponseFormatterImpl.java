package com.app.format;

import static com.app.util.AppConstants.DURATION;

import java.text.DecimalFormat;

import com.app.dto.CalculationResponse;

public class CalculationResponseFormatterImpl implements CalculationResponseFormatter {

	@Override
	public void formatResponse(CalculationResponse response, double rate, double totalRepayment) {
		if(response == null) {
			return;
		}
		DecimalFormat df1 = new DecimalFormat("#.#");
		DecimalFormat df2 = new DecimalFormat("#.##");
		response.setRate(df1.format(rate*100));
		response.setMonthlyTotalRepayment(df2.format(totalRepayment));
		response.setMonthlyRepayment(df2.format(totalRepayment / (DURATION * 12)));
	}

}
