package com.app.exception;

public class LoanNotPossibleException extends Exception {
	
	private String msg;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8039064308705798923L;
	
	public LoanNotPossibleException(String msg) {
		super(msg);
		this.msg = msg;
	}

}
