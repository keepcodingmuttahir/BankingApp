package com.redmath.bankingapp.balance;

import com.redmath.bankingapp.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Balance")
public class BalanceController {
    private final BalanceService service;
    public BalanceController(BalanceService service)
    {
        this.service=service;
    }

    @GetMapping
    public ResponseEntity<List<Balance>> findAll()
    {
        return  ResponseEntity.ok(service.findAll());
    }

}
