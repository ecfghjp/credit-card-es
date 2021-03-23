package com.ecfghjp.credit.domain.event;

import java.math.BigDecimal;

public class CreditCardCreatedEvent extends BaseEvent<String>{
	
	private String creditCardNumber;
	private BigDecimal creditLimit;

	public CreditCardCreatedEvent(String id, String creditCardNumber, BigDecimal creditLimit) {
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
