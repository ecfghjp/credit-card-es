package com.ecfghjp.credit.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@PostMapping("/pay")
	public CompletableFuture<String> pay(@RequestBody TransactionRequestDTO payRequest) {
		return creditService.payment(payRequest);
	}
	
	@PostMapping("/repay")
	public CompletableFuture<String> repay(@RequestBody TransactionRequestDTO repayRequest) {
		return creditService.repayment(repayRequest);
	}
	
	//this will go nto query service

	@PutMapping("/setup")
	public CompletableFuture<String>  createCreditCard(@RequestBody CreditCardRequestDTO creditCard) {
		return creditService.createCreditCard(creditCard);
	}
}
