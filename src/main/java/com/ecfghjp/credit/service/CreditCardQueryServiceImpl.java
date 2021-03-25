package com.ecfghjp.credit.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import com.ecfghjp.credit.domain.query.FindCreditCardQuery;
import com.ecfghjp.credit.domain.query.FindTransactionsQuery;
import com.ecfghjp.credit.service.repository.CreditCardView;
import com.ecfghjp.credit.service.repository.TransactionsView;

@Service
public class CreditCardQueryServiceImpl implements CreditCardQueryService {

	private EventStore eventStore;
	private QueryGateway queryGateway;

	public CreditCardQueryServiceImpl(EventStore eventStore,
			QueryGateway queryGateway) {
		this.eventStore = eventStore;
		this.queryGateway = queryGateway;
	}

	@Override
	public List<Object> listEventsForCreditCard(String eventId) {
		// TODO Auto-generated method stub
		return eventStore.readEvents(eventId).asStream().map(s -> s.getPayload()).collect(Collectors.toList());
	}

	@Override
	public CompletableFuture<CreditCardView> getCreditCardDetails(String creditCardNumber) {
		// TODO Auto-generated method stub
		return queryGateway.query(new FindCreditCardQuery(creditCardNumber),
				ResponseTypes.instanceOf(CreditCardView.class));
	}

	@Override
	public CompletableFuture<List<TransactionsView>> getAllCreditCardTransactions(String creditCardNumber) {
		// TODO Auto-generated method stub
		return queryGateway.query(new FindTransactionsQuery(creditCardNumber),
				ResponseTypes.multipleInstancesOf(TransactionsView.class));
	}

}
