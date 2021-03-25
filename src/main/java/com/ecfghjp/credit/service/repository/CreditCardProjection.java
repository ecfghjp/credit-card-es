package com.ecfghjp.credit.service.repository;

import java.util.Optional;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecfghjp.credit.domain.aggregate.CreditCardStatus;
import com.ecfghjp.credit.domain.event.CreditCardCreatedEvent;
import com.ecfghjp.credit.domain.event.CreditCardPaymentDoneEvent;
import com.ecfghjp.credit.domain.event.CreditCardRepaymentDoneEvent;
import com.ecfghjp.credit.domain.query.FindCreditCardQuery;

@Component
public class CreditCardProjection {

	private final CreditCardJPARepository creditCardJPARepository;

	@Autowired
	public CreditCardProjection(CreditCardJPARepository creditCardJPARepository) {
		this.creditCardJPARepository = creditCardJPARepository;
	}

	@EventHandler
	public void on(CreditCardCreatedEvent creditCardCreatedEvent) {
		CreditCardView creditCardView = new CreditCardView(creditCardCreatedEvent.getCreditCardNumber(),
				creditCardCreatedEvent.getCreditLimit());
		creditCardView.setStatus(String.valueOf(CreditCardStatus.ACTIVATED));
		creditCardJPARepository.save(creditCardView);
	}

	@EventHandler
	public void on(CreditCardPaymentDoneEvent creditCardPaymentDoneEvent) {
		Optional<CreditCardView> optional = creditCardJPARepository.findById(creditCardPaymentDoneEvent.getId());
		CreditCardView creditCardView = optional.get();
		creditCardView.pay(creditCardPaymentDoneEvent.getPaymentAmount());
		creditCardJPARepository.save(creditCardView);
	}

	@EventHandler
	public void on(CreditCardRepaymentDoneEvent creditCardRepaymentDoneEvent) {
		Optional<CreditCardView> optional = creditCardJPARepository.findById(creditCardRepaymentDoneEvent.getId());
		CreditCardView creditCardView = optional.get();
		creditCardView.repay(creditCardRepaymentDoneEvent.getRepaymentAmount());
		creditCardJPARepository.save(creditCardView);
	}

	@QueryHandler
	public CreditCardView on(FindCreditCardQuery query) {
		return creditCardJPARepository.findByCreditCardNumber(query.getAccountNumber()).orElse(null);
	}

}
