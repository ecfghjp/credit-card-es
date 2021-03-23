package com.ecfghjp.credit.domain.command;

import java.math.BigDecimal;

public class CreditCardRepaymentCommand extends BaseCommand<String> {

	private BigDecimal repaymentAmount;

	public CreditCardRepaymentCommand(String id, BigDecimal repaymentAmount) {
		super(id);
		this.repaymentAmount = repaymentAmount;
	}

	public BigDecimal getRepaymentAmount() {
		return repaymentAmount;
	}

}
