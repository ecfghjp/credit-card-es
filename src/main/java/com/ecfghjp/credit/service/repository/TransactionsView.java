package com.ecfghjp.credit.service.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ecfghjp.credit.domain.aggregate.TransactionPurpose;
import com.ecfghjp.credit.domain.aggregate.TransactionStatus;

@Entity
public class TransactionsView {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Long transactionId;
	private String creditCardNumber;
	private BigDecimal transactionAmount;
	private LocalDateTime transactionDate;
	private String transactionPurpose;
	private String creditCardTransactionStatus;
	
	public TransactionsView() {
	}
	
	
	public TransactionsView(String creditCardNumber, BigDecimal transactionAmount, LocalDateTime transactionDate,
			String transactionPurpose, String creditCardTransactionStatus) {
		super();
		this.creditCardNumber = creditCardNumber;
		this.transactionAmount = transactionAmount;
		this.transactionDate = transactionDate;
		this.transactionPurpose = transactionPurpose;
		this.creditCardTransactionStatus = creditCardTransactionStatus;
	}


	public Long getTransactionId() {
		return transactionId;
	}
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}
	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}
	public String getTransactionPurpose() {
		return transactionPurpose;
	}
	public String getCreditCardTransactionStatus() {
		return creditCardTransactionStatus;
	}

}
