package br.com.gfin.mywallet.controller;

import br.com.gfin.mywallet.controller.dto.CreateUpdateExpenseDto;
import br.com.gfin.mywallet.controller.dto.PaymentInfoDto;
import br.com.gfin.mywallet.controller.dto.response.ExpenseResponse;
import br.com.gfin.mywallet.entity.Expense;
import br.com.gfin.mywallet.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseResponse> create(@Valid @RequestBody CreateUpdateExpenseDto expenseDto){
        try {
            ExpenseResponse expenseResponse = expenseService.create(expenseDto);
            return new ResponseEntity<>(expenseResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Expense>> listPending(@Param("year") Integer year, @Param("month") Integer month){
        List<Expense> expenses = expenseService.findAllByDueMonth(year, month);
        return ResponseEntity.ok(expenses);
    }

    @PatchMapping(path = "/{expenseId}/pay")
    public ResponseEntity<ExpenseResponse> payExpense(@PathVariable Long expenseId, @Valid @RequestBody PaymentInfoDto paymentInfoDto){
        try {
            ExpenseResponse payed = expenseService.payExpense(expenseId, paymentInfoDto);
            return ResponseEntity.ok(payed);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
