package br.com.gfin.mywallet.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
public class ExpenseResponse {

    private final Long id;

    private final String description;

    private final CategoryResponse category;

    private final Double amount;

    private final PaymentResponse paymentInfo;

    @JsonFormat(pattern="dd/MM/yyyy")
    private final LocalDate dueDate;
}
