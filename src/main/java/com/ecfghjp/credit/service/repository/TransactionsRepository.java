package com.ecfghjp.credit.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionsRepository extends JpaRepository<TransactionsView, Long>{


	Optional<List<TransactionsView>> findAllByCreditCardNumber(String accountNumber);
}
