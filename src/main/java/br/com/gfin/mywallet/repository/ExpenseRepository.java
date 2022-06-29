package br.com.gfin.mywallet.repository;

import br.com.gfin.mywallet.entity.Expense;
import br.com.gfin.mywallet.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByPaymentStatusAndDueDateBetween(PaymentStatus paymentStatus, LocalDate dueDateStart, LocalDate dueDateEnd);
}
