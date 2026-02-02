package com.santxa.moneyLending.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.santxa.moneyLending.models.LoanStatus;
import com.santxa.moneyLending.models.Loans;

public interface LoansRepository extends JpaRepository<Loans, Long>{
   Optional<Loans> findByBorrowerIdAndStatusIn(long borroweId,List<LoanStatus> statuses);
   
   @Query("SELECT SUM(l.principalAmount) from Loans l where l.status = 'ACTIVE'")
   Double getTotalTransactions();
   
   List<Loans> findByStatus(LoanStatus status);
   
   List<Loans> findByPaymentDate(LocalDate paymentDate);
   List<Loans> findByApproveDate(LocalDate approveDate);
}
