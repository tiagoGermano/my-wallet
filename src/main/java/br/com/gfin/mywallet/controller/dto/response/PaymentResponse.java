package br.com.gfin.mywallet.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class PaymentResponse {

    private final LocalDate payedAt;

    private final Double amount;

    private final Double interest;

    private final Double discount;

}
