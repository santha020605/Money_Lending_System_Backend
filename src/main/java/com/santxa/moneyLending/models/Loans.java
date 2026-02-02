package com.santxa.moneyLending.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Loans")
public class Loans {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "borrower_id",nullable = false)
	private Borrower borrower;
	
	private double principalAmount;
	private double interestRate;
	private int months;
	private double totalAmount;
	
	@Enumerated(EnumType.STRING)
	private LoanStatus status;
	
	@Enumerated(EnumType.STRING)
	private LoanType type;
	
	private double emiAmount;
	private double totalPaid;
	private double remainingDue;
	
	private LocalDate appliedDate;
	private LocalDate approveDate;
	private LocalDate dueDate;
	private LocalDate paymentDate;
	
	

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}

	public LocalDate getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(LocalDate approveDate) {
		this.approveDate = approveDate;
	}

	public double getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(double totalPaid) {
		this.totalPaid = totalPaid;
	}

	public double getRemainingDue() {
		return remainingDue;
	}

	public void setRemainingDue(double remainingDue) {
		this.remainingDue = remainingDue;
	}

	public double getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(double emiAmount) {
		this.emiAmount = emiAmount;
	}

	public LoanType getType() {
		return type;
	}

	public void setType(LoanType type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public double getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(double principalAmount) {
		this.principalAmount = principalAmount;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LoanStatus getStatus() {
		return status;
	}

	public void setStatus(LoanStatus status) {
		this.status = status;
	}
	
}
