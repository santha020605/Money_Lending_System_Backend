package com.santxa.moneyLending.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santxa.moneyLending.models.Borrower;
import com.santxa.moneyLending.service.BorrowerService;

@RestController
@RequestMapping("/api/borrowers")
@CrossOrigin("*")
public class BorrowerController {
	
	BorrowerService service;
	
	BorrowerController(BorrowerService service){
		this.service = service;
	}
	
	@PostMapping("/register")
	public Borrower registerBorrow(@RequestBody  Borrower borrow) {
		return service.registerBorrower(borrow);
	}
	
	@GetMapping
	public List<Borrower> getBorrowers(){
		return service.getAllBorrowers();
	}
	@PostMapping("/login")
	public Borrower loginBorrower(@RequestBody Map<String,String> loginData) {
		String phone = loginData.get("phone");
		String password = loginData.get("password");
		return service.loginBorrower(phone, password);
	}
	@GetMapping("/{borrowerId}")
	public Borrower getBorrower(@PathVariable long borrowerId) {
		return service.getBorrower(borrowerId);
	}

}
