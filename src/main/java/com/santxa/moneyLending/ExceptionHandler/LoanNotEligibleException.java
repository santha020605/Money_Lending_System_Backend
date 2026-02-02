package com.santxa.moneyLending.ExceptionHandler;

public class LoanNotEligibleException extends RuntimeException{
	public LoanNotEligibleException(String message) {
		super(message);
	}

}
