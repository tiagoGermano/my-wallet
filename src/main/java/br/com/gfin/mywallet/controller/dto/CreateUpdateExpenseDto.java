package br.com.gfin.mywallet.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateExpenseDto {

    @NotNull
    private String description;

    @NotNull
    private Long categoryId;

    @NotNull
    private Double amount;

    @NotNull
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dueDate;

    private PaymentInfoDto paymentInfo;
}
