package com.ecfghjp.credit.service;

import java.util.concurrent.CompletableFuture;

import com.ecfghjp.credit.controller.dto.CreditCardRequestDTO;
import com.ecfghjp.credit.controller.dto.TransactionRequestDTO;


public interface CreditCardCommandService {
	
	public CompletableFuture<String> createCreditCard(CreditCardRequestDTO creditCardRequestDTO);

	public CompletableFuture<String> payment(TransactionRequestDTO paymentRequestDTO);
	
	public CompletableFuture<String> repayment( TransactionRequestDTO paymentRequestDTO);

}
