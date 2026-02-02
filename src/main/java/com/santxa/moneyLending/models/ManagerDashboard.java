package com.santxa.moneyLending.models;

public class ManagerDashboard {
	private double totalTransactions;
	private double todayTransactions;
	private double todayRepayments;
	
	public ManagerDashboard(double totalTransactions,double todayTransactions,double todayRepayments) {
		this.totalTransactions = totalTransactions;
		this.todayTransactions = todayTransactions;
		this.todayRepayments = todayRepayments;
	}

	public double getTodayRepayments() {
		return todayRepayments;
	}

	public void setTodayRepayments(double todayRepayments) {
		this.todayRepayments = todayRepayments;
	}

	public double getTotalTransactions() {
		return totalTransactions;
	}
	
	public void setTotalTransactions(double totalTransactions) {
		this.totalTransactions = totalTransactions;
	}

	public double getTodayTransactions() {
		return todayTransactions;
	}

	public void setTodayTransactions(double todayTransactions) {
		this.todayTransactions = todayTransactions;
	}
	

	

}
