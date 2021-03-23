package com.ecfghjp.credit.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.ecfghjp.credit.domain.aggregate.CreditCardStatus;
import com.ecfghjp.credit.domain.aggregate.TransactionStatus;
import com.ecfghjp.credit.domain.aggregate.TransactionPurpose;
import com.ecfghjp.credit.domain.command.CreateCreditCardCommand;
import com.ecfghjp.credit.domain.command.CreditCardPaymentCommand;
import com.ecfghjp.credit.domain.command.CreditCardRepaymentCommand;
import com.ecfghjp.credit.domain.event.CreditCardActivatedEvent;
import com.ecfghjp.credit.domain.event.CreditCardCreatedEvent;
import com.ecfghjp.credit.domain.event.CreditCardPaymentDoneEvent;
import com.ecfghjp.credit.domain.event.CreditCardRepaymentDoneEvent;
import com.ecfghjp.credit.exception.InvalidOperationException;
import com.ecfghjp.credit.exception.NotEnoughCreditException;

//Root Aggregate for creditCardTransactions
@Aggregate
public class CreditCardAggregate {
	// refactoring code to flaten the credit card
	@AggregateIdentifier
	private String id;
	// credit card details
	private String creditCardNumber;
	private BigDecimal balance;
	private BigDecimal transactionAmount;
	private LocalDateTime transactionDate;
	private TransactionPurpose transactionPurpose;
	private TransactionStatus creditCardTransactionStatus;
	private CreditCardStatus creditCardStatus;

	public CreditCardAggregate() {
	}

	public CreditCardAggregate(String id, String creditCardNumber, BigDecimal balance, BigDecimal transactionAmount,
			LocalDateTime transactionDate, TransactionPurpose transactionPurpose,
			TransactionStatus creditCardTransactionStatus, CreditCardStatus creditCardStatus) {
		this.id = id;
		this.creditCardNumber = creditCardNumber;
		this.balance = balance;
		this.transactionAmount = transactionAmount;
		this.transactionDate = transactionDate;
		this.transactionPurpose = transactionPurpose;
		this.creditCardTransactionStatus = creditCardTransactionStatus;
		this.creditCardStatus = creditCardStatus;
	}

	// refactor the aggregate to a command and event and event handler class
	// command to create credit card
	@CommandHandler
	public CreditCardAggregate(CreateCreditCardCommand createCreditCardCommand) {
		AggregateLifecycle.apply(new CreditCardCreatedEvent(createCreditCardCommand.getId(),
				createCreditCardCommand.getCreditCardNumber(), createCreditCardCommand.getCreditLimit()));

	}

	@EventSourcingHandler
	public void on(CreditCardCreatedEvent creditCardCreatedEvent) {
		this.id = creditCardCreatedEvent.getId();
		this.creditCardNumber = creditCardCreatedEvent.getCreditCardNumber();
		this.balance = creditCardCreatedEvent.getCreditLimit();
		this.creditCardStatus = CreditCardStatus.LIMIT_ASSIGNED;
		AggregateLifecycle
				.apply(new CreditCardActivatedEvent(creditCardCreatedEvent.getId(), CreditCardStatus.ACTIVATED));
	}

	@EventSourcingHandler
	public void on(CreditCardActivatedEvent creditCardActivatedEvent) {
		this.creditCardStatus = creditCardActivatedEvent.getCreditCardStatus();
	}

	@CommandHandler
	public void on(CreditCardPaymentCommand paymentCommand) {
		// pay money into credit card command
		if (null == this.balance) {
			throw new InvalidOperationException("Credit Limit Not Assigned");
		}
		if (paymentCommand.getPaymentAmount().compareTo(this.balance) > 0) {
			throw new NotEnoughCreditException();

		}
		if (paymentCommand.getPaymentAmount().equals(new BigDecimal(0))) {
			throw new InvalidOperationException("Payment amount should be greater than 0");
		}
		AggregateLifecycle
				.apply(new CreditCardPaymentDoneEvent(paymentCommand.getId(), paymentCommand.getPaymentAmount()));

	}

	@EventSourcingHandler
	public void on(CreditCardPaymentDoneEvent creditCardPaymentEvent) {
		// handle credit card payment event
		// validations can sit here

		this.transactionAmount = creditCardPaymentEvent.getPaymentAmount();
		this.transactionDate = LocalDateTime.now();
		this.transactionPurpose = TransactionPurpose.PAYMENT;
		this.creditCardTransactionStatus = TransactionStatus.SUCCESS;
		this.balance = this.balance.subtract(creditCardPaymentEvent.getPaymentAmount());
	}

	@CommandHandler
	public void on(CreditCardRepaymentCommand creditCardRepaymentCommand) {
		if (null == this.balance) {
			this.creditCardTransactionStatus = TransactionStatus.FAILURE;
			throw new InvalidOperationException("Credit Limit Not Assigned");
		}
		AggregateLifecycle.apply(new CreditCardRepaymentDoneEvent(creditCardRepaymentCommand.getId(),
				creditCardRepaymentCommand.getRepaymentAmount()));

	}

	@EventSourcingHandler
	public void on(CreditCardRepaymentDoneEvent creditCardRepaymentDoneEvent) {
		this.transactionAmount = creditCardRepaymentDoneEvent.getRepaymentAmount();
		this.transactionDate = LocalDateTime.now();
		this.transactionPurpose = TransactionPurpose.REPAYMENT;
		this.creditCardTransactionStatus = TransactionStatus.SUCCESS;
		this.balance = this.balance.add(creditCardRepaymentDoneEvent.getRepaymentAmount());

	}
	// end handlers

	public String getId() {
		return id;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public TransactionPurpose getTransactionPurpose() {
		return transactionPurpose;
	}

	public TransactionStatus getCreditCardTransactionStatus() {
		return creditCardTransactionStatus;
	}

	public CreditCardStatus getCreditCardStatus() {
		return creditCardStatus;
	}

}
