package com.ecfghjp.credit.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.ecfghjp.credit.service.repository.CreditCardView;
import com.ecfghjp.credit.service.repository.TransactionsView;

@Service
public interface CreditCardQueryService {
	
    public List<Object> listEventsForCreditCard(String eventId);
    public CompletableFuture<CreditCardView> getCreditCardDetails(String creditCardNumber);
    public CompletableFuture<List<TransactionsView>> getAllCreditCardTransactions(String creditCardNumber);



}
