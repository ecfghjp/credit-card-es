package com.ecfghjp.credit.domain.command;

import java.math.BigDecimal;

public class CreateCreditCardCommand extends BaseCommand<String> {

	private String creditCardNumber;
	private BigDecimal creditLimit;

	public CreateCreditCardCommand(String id, String creditCardNumber, BigDecimal creditLimit) {
		super(id);
		this.creditCardNumber = creditCardNumber;
		this.creditLimit = creditLimit;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

}
