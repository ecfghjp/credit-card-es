package com.ecfghjp.credit.service;

import java.util.concurrent.CompletableFuture;

import com.ecfghjp.credit.controller.dto.CreditCardRequestDTO;
import com.ecfghjp.credit.controller.dto.TransactionRequestDTO;


public interface CreditCardCommandService {
	
	public CompletableFuture<String> createCreditCard(CreditCardRequestDTO creditCardRequestDTO);

	public CompletableFuture<String> payment(String eventId,TransactionRequestDTO paymentRequestDTO);
	
	public CompletableFuture<String> repayment(String eventId, TransactionRequestDTO paymentRequestDTO);

}
