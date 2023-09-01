package com.redmath.bankingapp.user;
import com.redmath.bankingapp.balance.Balance;
import com.redmath.bankingapp.balance.BalanceService;
import com.redmath.bankingapp.transaction.Transaction;
import com.redmath.bankingapp.transaction.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/Users")
public class UserController {
    private final UserService service;
    private final BalanceService balanceService;
    private final UserDetailsDTO userDetailsDTO;
    private final TransactionService transactionService;

    public UserController(UserService service, BalanceService balanceService, UserDetailsDTO userAccountDetails, TransactionService transactionService) {
        this.service = service;
        this.balanceService = balanceService;
        this.userDetailsDTO = userAccountDetails;
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll()
    {
        return  ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User created = service.create(user);
        if (created != null) {
            return ResponseEntity.ok(created);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    @GetMapping(value = "/{id}/all")
    public ResponseEntity<UserDetailsDTO> UserDashboard(@PathVariable("id") Long id) {
        User user= service.findById(id);
        Balance balance=balanceService.findById(id);
        List<Transaction> transaction =transactionService.findByUserId(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userDetailsDTO.setUser(user);
        userDetailsDTO.setBalance(balance);
        userDetailsDTO.setTransactions(transaction);
        return ResponseEntity.ok(userDetailsDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        User user = service.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        User user = service.findById(id);
        if (user==null) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") long id,@RequestBody User user) {

        User up = service.update(user, id);
        if (up==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(up);
    }
}