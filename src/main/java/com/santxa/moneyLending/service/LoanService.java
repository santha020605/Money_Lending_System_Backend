package com.santxa.moneyLending.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.santxa.moneyLending.ExceptionHandler.LoanNotEligibleException;
import com.santxa.moneyLending.models.Borrower;
import com.santxa.moneyLending.models.LoanStatus;
import com.santxa.moneyLending.models.LoanType;
import com.santxa.moneyLending.models.Loans;
import com.santxa.moneyLending.repository.BorrowerRepository;
import com.santxa.moneyLending.repository.LoansRepository;

@Service
public class LoanService {
	BorrowerRepository borrowerRepository;
	LoansRepository loanRepository;
	
	LoanService(BorrowerRepository borrowerRepository,LoansRepository loanRepository){
		this.borrowerRepository = borrowerRepository;
		this.loanRepository = loanRepository;
	}
	public Loans applyLoan(long borrowerId,Loans loan) {
		Borrower borrower = borrowerRepository.findById(borrowerId).orElseThrow(()-> new RuntimeException("Borrower not Found"));
		if(getAllLoans().contains(borrower)) {
			throw new RuntimeException("Loan Already Existed.");
		}
		
		double income = borrower.getMonthlyIncome();
		double principalAmount = loan.getPrincipalAmount();
		
		switch(loan.getType()) {
		case HOME:
			if(principalAmount>2500000 || income<25000) {
				throw new LoanNotEligibleException("Not Eligible for HOME Loan(Income is Must be above 25K and Maximum Loan amount is 25 Lakhs)");
			}
			break;
		case PERSONAL:
			if(principalAmount>500000 || income<20000) {
				throw new LoanNotEligibleException("Not Eligible for Personnal Loan(Income is Must be above 20K and Maximum Loan amount is 5 Lakhs)");
			}
			break;
		case BUSINESS:
			if(principalAmount>5000000 || income<50000) {
				throw new LoanNotEligibleException("Not Eligible for Business Loan(Income is Must be above 50K and Maximum Loan amount is 50 Lakhs)");
			}
			break;
		}
		loan.setBorrower(borrower);
		loan.setStatus(LoanStatus.APPLIED);
		loan.setAppliedDate(LocalDate.now());
		
		return loanRepository.save(loan);
	}
	
	public Loans approveLoan(long loanId) {
		
		Loans loan = loanRepository.findById(loanId).orElseThrow(()-> new RuntimeException("Loans not Found"));
		if(loan.getStatus()!=LoanStatus.APPLIED) {
			throw new RuntimeException("Loan not in Applied State");
		}
		
		double principal = loan.getPrincipalAmount();
		double interest = interestRateOf(loan.getType());
		
		double emi = calculateEmi(principal, interest, loan.getMonths());
		loan.setTotalAmount(emi*loan.getMonths());
		loan.setEmiAmount(emi);
		loan.setTotalPaid(0);
		loan.setRemainingDue(loan.getTotalAmount());
		loan.setInterestRate(interest);
		loan.setStatus(LoanStatus.ACTIVE);
		loan.setApproveDate(LocalDate.now());
		loan.setDueDate(loan.getApproveDate().plusMonths(1));
		
		return loanRepository.save(loan);
	}
	public Loans rejectLoan(long loanId) {
		Loans loan = loanRepository.findById(loanId).orElseThrow(()-> new RuntimeException("Loan not found"));
		if(loan.getStatus()!=LoanStatus.APPLIED) {
			throw new RuntimeException("Loan not in the applied State");
		}
		loan.setStatus(LoanStatus.REJECTED);
		return loanRepository.save(loan);
	}
	public Loans getCurrentLoan(long borrowerId) {
		 return loanRepository.findByBorrowerIdAndStatusIn(borrowerId, List.of(LoanStatus.ACTIVE,LoanStatus.APPLIED)).orElseThrow(()-> new RuntimeException("Loan not Found"));
	
	}
	public double interestRateOf(LoanType loanType) {
		return switch(loanType) {
		case PERSONAL -> 12;
		case BUSINESS -> 14;
		case HOME -> 8;
		};
	}
	public double calculateEmi(double p,double r,int m) {
		r = r/(12*100);//Annual Interst into months 
		
		
		return (p*r*Math.pow(r+1, m))/(Math.pow(r+1, m)-1);
	}
	public Loans repayEmi(long borrowerId) {
		Loans loan = getCurrentLoan(borrowerId);
		if(loan.getStatus()!=LoanStatus.ACTIVE) {
			throw new RuntimeException("Loan not Active");
		}
		loan.setTotalPaid(loan.getTotalPaid()+loan.getEmiAmount());
		loan.setRemainingDue(loan.getTotalAmount() - loan.getTotalPaid());
		loan.setPaymentDate(LocalDate.now());
		loan.setDueDate(LocalDate.now().plusMonths(1));
		if(loan.getRemainingDue()<=0) {
			loan.setStatus(LoanStatus.COMPLETED);
		}
		return loanRepository.save(loan);
	}
	public List<Loans> getActiveLoans(){
		return loanRepository.findByStatus(LoanStatus.ACTIVE);
	}
	public List<Loans> getLoanRequests(){
		return loanRepository.findByStatus(LoanStatus.APPLIED);
	}
	public List<Loans> getAllLoans(){
		return loanRepository.findAll();
	}
	public double getTodayCollection() {
		List<Loans> loan = loanRepository.findByPaymentDate(LocalDate.now());
		return loan.stream().mapToDouble(Loans::getEmiAmount).sum();
	}
	public double getTodayTransactions() {
		List<Loans> loan = loanRepository.findByApproveDate(LocalDate.now());
		return loan.stream().mapToDouble(Loans::getPrincipalAmount).sum();
	}
}




















