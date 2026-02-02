package com.santxa.moneyLending.service;


import org.springframework.stereotype.Service;

import com.santxa.moneyLending.models.Manager;
import com.santxa.moneyLending.models.ManagerDashboard;

import com.santxa.moneyLending.repository.LoansRepository;
import com.santxa.moneyLending.repository.ManagerRepository;

@Service
public class ManagerService {
	
	private final ManagerRepository managerRepository;
	private final LoansRepository loanRepository;
	private LoanService loanService;
	
	public ManagerService(ManagerRepository managerRepository,LoansRepository loanRepository,LoanService loanService) {
		this.managerRepository = managerRepository;
		this.loanService = loanService;
		this.loanRepository = loanRepository;
	}
	
	public Manager loginManager(String username,String password) {
		return managerRepository.findByUsernameAndPassword(username, password).orElseThrow(()-> new RuntimeException("Invalid Credentials"));
	}
	
	public ManagerDashboard getDashBoard() {
		Double totalTransactions = loanRepository.getTotalTransactions();
		if(loanRepository.getTotalTransactions()==null) {
			totalTransactions = 0.0;
		}
		return new ManagerDashboard(totalTransactions,loanService.getTodayTransactions(),loanService.getTodayCollection());
	}

}
