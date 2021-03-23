package com.ecfghjp.credit.domain.event;

import com.ecfghjp.credit.domain.aggregate.CreditCardStatus;
import com.ecfghjp.credit.domain.event.BaseEvent;

public class CreditCardActivatedEvent extends BaseEvent<String> {
	private CreditCardStatus creditCardStatus;

	public CreditCardActivatedEvent(String id, CreditCardStatus creditCardStatus) {
		super(id);
		this.creditCardStatus = creditCardStatus;
	}

	public CreditCardStatus getCreditCardStatus() {
		return creditCardStatus;
	}

}
