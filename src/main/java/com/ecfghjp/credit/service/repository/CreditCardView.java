package com.ecfghjp.credit.service.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

//for projection
@Entity
public class CreditCardView {
	@Id
	private String creditCardNumber;
	private BigDecimal balance;
	private String status;

	public CreditCardView() {
	}

	public CreditCardView(String creditCardNumber, BigDecimal balance) {
		this.creditCardNumber = creditCardNumber;
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void pay(BigDecimal amount) {
		this.balance.subtract(amount);

	}

	public void repay(BigDecimal amount) {
		this.balance.add(amount);

	}
}
