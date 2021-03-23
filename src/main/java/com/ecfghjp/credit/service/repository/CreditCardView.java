package com.ecfghjp.credit.service.repository;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

//for projection
@Entity
public class CreditCardView {
	@Id
	private String creditCardNumber;
	private BigDecimal balance;


	public CreditCardView() {
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
