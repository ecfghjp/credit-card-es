package com.ecfghjp.credit.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecfghjp.credit.controller.dto.CreditCardRequestDTO;
import com.ecfghjp.credit.controller.dto.TransactionRequestDTO;
import com.ecfghjp.credit.service.CreditCardCommandService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/credit")
@Api(value = "Creditcard commands", description = "Creditcard command Events Endpoint", tags = "Creditcard commands")
public class CreditCardCommandController {

	private CreditCardCommandService creditService;
	
	@Autowired
	public CreditCardCommandController(CreditCardCommandService creditService) {
		this.creditService = creditService;
	}

	@PostMapping("/pay/{credit-card-number}")
	public CompletableFuture<String> pay(@RequestParam(value = "credit-card-number") String creditCardNumber,@RequestBody TransactionRequestDTO withdrawalRequest) {
		return creditService.payment(creditCardNumber,withdrawalRequest);
	}
	
	@PostMapping("/repay/{credit-card-number}")
	public CompletableFuture<String> repay(@RequestParam(value = "credit-card-number") String creditCardNumber,@RequestBody TransactionRequestDTO withdrawalRequest) {
		return creditService.repayment(creditCardNumber,withdrawalRequest);
	}
	
	//this will go nto query service

	@PutMapping("/assign/")
	public CompletableFuture<String>  createCreditCard(@RequestBody CreditCardRequestDTO creditCard) {
		return creditService.createCreditCard(creditCard);
	}
}
