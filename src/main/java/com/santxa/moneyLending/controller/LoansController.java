package com.santxa.moneyLending.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santxa.moneyLending.models.Loans;
import com.santxa.moneyLending.service.LoanService;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin("*")
public class LoansController {
	
	private final LoanService loanService;
	
	LoansController(LoanService loanService){
		this.loanService = loanService;
	}
	
	@PostMapping("/apply/{borrowerId}")
	public Loans applyLoan(@PathVariable long borrowerId,@RequestBody Loans loan) {
		return loanService.applyLoan(borrowerId,loan);
	}
	
	@PutMapping("approve/{loanId}")
	public Loans approveLoan(@PathVariable long loanId) {
		return loanService.approveLoan(loanId);
	}
	
	@PutMapping("reject/{loanId}")
	public Loans rejectLoan(@PathVariable long loanId) {
		return loanService.rejectLoan(loanId);
	}
	
	@GetMapping("/current/{borrowerId}")
	public Loans getCurrentLoan(@PathVariable long borrowerId) {
		return loanService.getCurrentLoan(borrowerId);
	}
	
	@PutMapping("/repayment/{borrowerId}")
	public Loans repayEmi(@PathVariable long borrowerId) {
		return loanService.repayEmi(borrowerId);
	}
	
	@GetMapping("/all")
	public List<Loans> getAll(){
		return loanService.getAllLoans();
	}
	
	@GetMapping("/activeLoans")
	public List<Loans> getActiveLoans(){
		return loanService.getActiveLoans();
	}
	@GetMapping("/appliedLoans")
	public List<Loans> getAppliedLoans(){
		return loanService.getLoanRequests();
	}


}
