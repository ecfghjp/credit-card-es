package com.ecfghjp.credit.domain.command;

import java.math.BigDecimal;

public class CreditCardPaymentCommand extends BaseCommand<String> {

	private BigDecimal paymentAmount;

	public CreditCardPaymentCommand(String id, BigDecimal paymentAmount) {
		super(id);
		this.paymentAmount = paymentAmount;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

}
