package com.ecfghjp.credit.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardJPARepository extends JpaRepository<CreditCardView, String>{

	Optional<CreditCardView> findByCreditCardNumber(String accountNumber);

}
