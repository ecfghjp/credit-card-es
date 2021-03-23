package com.ecfghjp.credit.domain.event;

import java.math.BigDecimal;

public class CreditCardRepaymentDoneEvent extends BaseEvent<String> {

	private BigDecimal repaymentAmount;

	public CreditCardRepaymentDoneEvent(String id, BigDecimal repaymentAmount) {
		super(id);
		this.repaymentAmount = repaymentAmount;
	}

	public BigDecimal getRepaymentAmount() {
		return repaymentAmount;
	}

}
