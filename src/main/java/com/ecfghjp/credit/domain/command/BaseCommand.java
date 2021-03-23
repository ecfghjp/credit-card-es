package com.ecfghjp.credit.domain.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommand<T> {
	
	@TargetAggregateIdentifier
	private T id;

	public BaseCommand(T id) {
		this.id = id;
	}

	public T getId() {
		return id;
	}
	

}
