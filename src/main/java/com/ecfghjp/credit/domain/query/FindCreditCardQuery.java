package com.ecfghjp.credit.domain.query;

public class FindCreditCardQuery {

	private String accountNumber;

	public FindCreditCardQuery() {
		// TODO Auto-generated constructor stub
	}

	public FindCreditCardQuery(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

}
