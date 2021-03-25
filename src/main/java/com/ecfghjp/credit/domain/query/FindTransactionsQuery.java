package com.ecfghjp.credit.domain.query;

public class FindTransactionsQuery {

	private String accountNumber;

	public FindTransactionsQuery() {
		// TODO Auto-generated constructor stub
	}

	public FindTransactionsQuery(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

}
