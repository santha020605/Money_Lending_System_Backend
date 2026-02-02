package com.santxa.moneyLending.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.santxa.moneyLending.models.Borrower;

public interface BorrowerRepository extends JpaRepository<Borrower, Long>{
	Optional<Borrower> findByPhone(String phone);
	Optional<Borrower> findByPhoneAndPassword(String phone,String password);
	
	@Query("SELECT COUNT(b) FROM Borrower b")
	long getAllBorrowers();

}
