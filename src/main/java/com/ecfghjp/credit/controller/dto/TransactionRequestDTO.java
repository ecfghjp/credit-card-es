package com.ecfghjp.credit.controller.dto;

import java.math.BigDecimal;

import com.ecfghjp.credit.domain.aggregate.TransactionPurpose;

public class TransactionRequestDTO {

	private String creditCardNumber;
	private BigDecimal transactionAmount;

	public TransactionRequestDTO(String creditCardNumber, BigDecimal transactionAmount,
			TransactionPurpose transactionPurpose) {
		this.creditCardNumber = creditCardNumber;
		this.transactionAmount = transactionAmount;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

}
