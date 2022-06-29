package br.com.gfin.mywallet.controller.dto.response;

import br.com.gfin.mywallet.entity.Expense;
import br.com.gfin.mywallet.entity.Payment;
import br.com.gfin.mywallet.entity.PaymentStatus;

public class ExpenseResponseBuilder {

    public static ExpenseResponse build(Expense expense){
        CategoryResponse category = CategoryResponse.builder()
                .id(expense.getCategory().getId())
                .description(expense.getCategory().getDescription())
                .build();

        return ExpenseResponse.builder()
                .category(category)
                .amount(expense.getAmount())
                .dueDate(expense.getDueDate())
                .description(expense.getDescription())
                .id(expense.getId())
                .paymentInfo(
                        PaymentStatus.PAYED.equals(expense.getPaymentStatus()) ?
                                buildPaymentInfo(expense.getPayment()) : null
                )
                .build();
    }

    private static PaymentResponse buildPaymentInfo(Payment payment){
        return PaymentResponse.builder()
                .amount(payment.getAmount())
                .discount(payment.getDiscount())
                .interest(payment.getInterest())
                .payedAt(payment.getPayedAt())
                .build();
    }
}
