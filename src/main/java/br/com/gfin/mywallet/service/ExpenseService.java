package br.com.gfin.mywallet.service;

import br.com.gfin.mywallet.controller.dto.CreateUpdateExpenseDto;
import br.com.gfin.mywallet.controller.dto.PaymentInfoDto;
import br.com.gfin.mywallet.controller.dto.response.ExpenseResponse;
import br.com.gfin.mywallet.controller.dto.response.ExpenseResponseBuilder;
import br.com.gfin.mywallet.entity.Category;
import br.com.gfin.mywallet.entity.Expense;
import br.com.gfin.mywallet.entity.Payment;
import br.com.gfin.mywallet.entity.PaymentStatus;
import br.com.gfin.mywallet.repository.CategoryRepository;
import br.com.gfin.mywallet.repository.ExpenseRepository;
import br.com.gfin.mywallet.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ExpenseService {

    private ExpenseRepository expenseRepository;
    private CategoryRepository categoryRepository;
    private PaymentRepository paymentRepository;

    @Transactional
    public ExpenseResponse create(CreateUpdateExpenseDto newExpenseDto) throws Exception {
        Optional<Category> category = categoryRepository.findById(newExpenseDto.getCategoryId());
        if (category.isEmpty()) {
            throw new Exception("invalid category");
        }
        Expense newExpense = Expense.builder()
                .description(newExpenseDto.getDescription())
                .amount(newExpenseDto.getAmount())
                .category(category.get())
                .dueDate(newExpenseDto.getDueDate())
                .paymentStatus(PaymentStatus.PENDING)
                .build();

        Expense expense = expenseRepository.save(newExpense);

        if(newExpenseDto.getPaymentInfo() != null){
            return doPayExpense(expense, newExpenseDto.getPaymentInfo());
        }

        return ExpenseResponseBuilder.build(expense);
    }

    @Transactional
    public ExpenseResponse payExpense(Long expenseId, PaymentInfoDto paymentInfoDto) throws Exception {
        Optional<Expense> expenseOptional = expenseRepository.findById(expenseId);
        if (expenseOptional.isEmpty()){
            throw new Exception("invalid expenseId");
        }
        Expense expense = expenseOptional.get();

        if (PaymentStatus.PAYED.equals(expense.getPaymentStatus())){
            throw new Exception("payment already done");
        }

        return doPayExpense(expense, paymentInfoDto);
    }

    private ExpenseResponse doPayExpense(Expense expense, PaymentInfoDto paymentInfoDto) {
        Payment payment = buildPaymentInfoModel(expense, paymentInfoDto);
        expense.setPaymentStatus(PaymentStatus.PAYED);
        expense.setPayment(payment);
        expenseRepository.save(expense);

        paymentRepository.save(payment);
        return ExpenseResponseBuilder.build(expense);
    }

    public List<Expense> findAllByDueMonth(int year, int month) {
        LocalDate beginOfMount = LocalDate.of(year, month, 1);
        LocalDate endOfMount = beginOfMount.plus(1, ChronoUnit.MONTHS).minus(1, ChronoUnit.DAYS);
        return expenseRepository.findByPaymentStatusAndDueDateBetween(PaymentStatus.PENDING, beginOfMount, endOfMount);
    }

    private Payment buildPaymentInfoModel(Expense expense, PaymentInfoDto paymentInfoDto){
        return Payment.builder()
                .expense(expense)
                .amount(paymentInfoDto.getAmount())
                .discount(paymentInfoDto.getDiscount() == null ? 0 : paymentInfoDto.getDiscount())
                .interest(paymentInfoDto.getInterest() == null ? 0 : paymentInfoDto.getInterest())
                .payedAt(paymentInfoDto.getPayedAt())
                .build();
    }

}
