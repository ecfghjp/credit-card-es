package com.ecfghjp.credit.service.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.ecfghjp.credit.domain.aggregate.TransactionPurpose;
import com.ecfghjp.credit.domain.aggregate.TransactionStatus;
import com.ecfghjp.credit.domain.event.CreditCardPaymentDoneEvent;
import com.ecfghjp.credit.domain.event.CreditCardRepaymentDoneEvent;
import com.ecfghjp.credit.domain.query.FindTransactionsQuery;

@Component
public class TransactionsProjection {

	private final TransactionsRepository transactionsRepository;

	public TransactionsProjection(TransactionsRepository transactionsRepository) {
		this.transactionsRepository = transactionsRepository;
	}

	@EventHandler
	public void on(CreditCardPaymentDoneEvent creditCardPaymentDoneEvent) {
		TransactionsView transactionsView = new TransactionsView(creditCardPaymentDoneEvent.getId(),
				creditCardPaymentDoneEvent.getPaymentAmount(), LocalDateTime.now(),
				String.valueOf(TransactionPurpose.PAYMENT), String.valueOf(TransactionStatus.SUCCESS));
		transactionsRepository.save(transactionsView);
	}

	@EventHandler
	public void on(CreditCardRepaymentDoneEvent creditCardRepaymentDoneEvent) {
		TransactionsView transactionsView = new TransactionsView(creditCardRepaymentDoneEvent.getId(),
				creditCardRepaymentDoneEvent.getRepaymentAmount(), LocalDateTime.now(),
				String.valueOf(TransactionPurpose.REPAYMENT), String.valueOf(TransactionStatus.SUCCESS));
		transactionsRepository.save(transactionsView);
	}
	
	@QueryHandler
	public List<TransactionsView> on(FindTransactionsQuery query) {
		List<TransactionsView> list =  transactionsRepository.findAllByCreditCardNumber(query.getAccountNumber()).orElse(null);
		return list;
	}

}
