package com.ecfghjp.credit.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecfghjp.credit.service.CreditCardQueryService;
import com.ecfghjp.credit.service.repository.CreditCardView;
import com.ecfghjp.credit.service.repository.TransactionsView;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/credit")
@Api(value = "Creditcard Queries", description = "Creditcard Query Events Endpoint", tags = "Creditcard Queries")
public class CreditCardQueryController {

	private final CreditCardQueryService creditCardQueryService;
	
	@Autowired
	public CreditCardQueryController(CreditCardQueryService creditCardQueryService) {
		this.creditCardQueryService = creditCardQueryService;
	}

	@GetMapping("/{accountNumber}/events")
	public List<Object> listEventsForAccount(@PathVariable(value = "accountNumber") String accountNumber) {
		return creditCardQueryService.listEventsForCreditCard(accountNumber);
	}
	
	@GetMapping("/transactions/{accountNumber}")
	public CompletableFuture<List<TransactionsView>> listAllTransactions(@PathVariable(value = "accountNumber") String accountNumber) {
		return creditCardQueryService.getAllCreditCardTransactions(accountNumber);
	}
	
	@GetMapping("/{accountNumber}")
	public CompletableFuture<CreditCardView> getCreditCardState(@PathVariable(value = "accountNumber") String accountNumber) {
		return creditCardQueryService.getCreditCardDetails(accountNumber);
	}
}
