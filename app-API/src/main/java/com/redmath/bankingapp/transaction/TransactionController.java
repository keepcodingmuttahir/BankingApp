package com.redmath.bankingapp.transaction;

import com.redmath.bankingapp.balance.Balance;
import com.redmath.bankingapp.balance.BalanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Transaction")
public class TransactionController {
    private final TransactionService service;
    private final BalanceService balanceService;
    public TransactionController(TransactionService service, BalanceService balanceService) {
        this.service = service;
        this.balanceService = balanceService;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> findAll()
    {
        return  ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/{user_id}")
    public ResponseEntity<Transaction> create(@RequestBody Transaction transaction, @PathVariable Long user_id) {
        Transaction created = service.create(transaction, user_id);
        if (created != null) {
            return ResponseEntity.ok(created);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}
