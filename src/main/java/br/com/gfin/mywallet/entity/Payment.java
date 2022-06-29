package br.com.gfin.mywallet.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "expense_id")
    private Expense expense;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "interest")
    private Double interest;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "payed_at", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDate payedAt;

}
