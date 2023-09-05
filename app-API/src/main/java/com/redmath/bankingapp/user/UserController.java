package com.redmath.bankingapp.user;
import com.redmath.bankingapp.balance.Balance;
import com.redmath.bankingapp.balance.BalanceService;
import com.redmath.bankingapp.transaction.Transaction;
import com.redmath.bankingapp.transaction.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Map<String ,List<User>>> findAll()
    {
        return  ResponseEntity.ok(Map.of("content", service.findAll()));
    }


    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> create(@RequestBody User user) {
        User created = service.create(user);
        if (created != null) {
            return ResponseEntity.ok(created);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping(value = "/{id}/all")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Map<String, UserDetailsDTO>> UserDashboard(@PathVariable("id") Long id) {
        User user= service.findById(id);
        Balance balance=balanceService.findById(id);
        List<Transaction> transaction =transactionService.findByUserId(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userDetailsDTO.setUser(user);
        userDetailsDTO.setBalance(balance);
        userDetailsDTO.setTransactions(transaction);
        return ResponseEntity.ok(Map.of("content",userDetailsDTO));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        User user = service.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        User user = service.findById(id);
        if (user==null) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> update(@PathVariable("id") long id,@RequestBody User user) {

        User up = service.update(user, id);
        if (up==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(up);
    }

//    @GetMapping
//    public ResponseEntity<Map<String,List<User>>> findAllByName(
//            @RequestParam(name = "title", defaultValue = "") String title) {
//        List<User> user = service.findAllByUserName(title);
//        if (user.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(Map.of("content",user));
//    }

}
