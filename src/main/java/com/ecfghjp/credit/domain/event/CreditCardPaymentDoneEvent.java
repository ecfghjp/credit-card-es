package com.ecfghjp.credit.domain.event;

import java.math.BigDecimal;

public class CreditCardPaymentDoneEvent extends BaseEvent<String> {
	
	private BigDecimal paymentAmount;

	public CreditCardPaymentDoneEvent(String id, BigDecimal paymentAmount) {
		super(id);
		this.paymentAmount = paymentAmount;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

}
