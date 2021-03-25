package com.ecfghjp.credit.service;

import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecfghjp.credit.controller.dto.CreditCardRequestDTO;
import com.ecfghjp.credit.controller.dto.TransactionRequestDTO;
import com.ecfghjp.credit.domain.command.CreateCreditCardCommand;
import com.ecfghjp.credit.domain.command.CreditCardPaymentCommand;
import com.ecfghjp.credit.domain.command.CreditCardRepaymentCommand;

@Service
public class CreditCardCommandServiceImpl implements CreditCardCommandService {

	private CommandGateway commandGateway;

	public CreditCardCommandServiceImpl() {
	}

	@Autowired
	public CreditCardCommandServiceImpl(CommandGateway commandGateway) {

		this.commandGateway = commandGateway;

	}

	@Override
	public CompletableFuture<String> payment(TransactionRequestDTO paymentRequestDTO) {

		return commandGateway.send(new CreditCardPaymentCommand(paymentRequestDTO.getCreditCardNumber(), paymentRequestDTO.getTransactionAmount()));
	}

	@Override
	public CompletableFuture<String> repayment(TransactionRequestDTO paymentRequestDTO) {

		return commandGateway.send(new CreditCardRepaymentCommand(paymentRequestDTO.getCreditCardNumber(), paymentRequestDTO.getTransactionAmount()));

	}

	@Override
	public CompletableFuture<String> createCreditCard(CreditCardRequestDTO creditCardRequestDTO) {
		// TODO Auto-generated method stub
		return commandGateway.send(new CreateCreditCardCommand(creditCardRequestDTO.getCreditCardNumber(),
				creditCardRequestDTO.getCreditCardNumber(), creditCardRequestDTO.getLimitAssigned()));

	}

}
