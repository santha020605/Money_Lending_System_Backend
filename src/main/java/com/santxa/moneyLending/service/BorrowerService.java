package com.santxa.moneyLending.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.santxa.moneyLending.models.Borrower;
import com.santxa.moneyLending.repository.BorrowerRepository;

@Service
public class BorrowerService {

	BorrowerRepository repository;
	
	BorrowerService(BorrowerRepository repository){
		this.repository = repository;
	}
	public Borrower registerBorrower(Borrower borrower) {
		if(repository.findByPhone(borrower.getPhone()).isPresent()) {
			throw new RuntimeException("Phone number already exists.");
		}
		return repository.save(borrower);
	}
	public List<Borrower> getAllBorrowers(){
		return repository.findAll();
	}
	public Borrower loginBorrower(String phone,String password) {
		return repository.findByPhoneAndPassword(phone, password).orElseThrow(()-> new RuntimeException("Invalid phone or password"));
	}
	public Borrower getBorrower(long borrowerId) {
		return repository.findById(borrowerId).orElseThrow(()-> new RuntimeException("Borrower Not found"));
	}
}
