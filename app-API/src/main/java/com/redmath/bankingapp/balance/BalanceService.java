package com.redmath.bankingapp.balance;

import com.redmath.bankingapp.user.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class BalanceService {

    private final BalanceRepository repository;
    public BalanceService( BalanceRepository repository)
    {
        this.repository=repository;
    }

    public List<Balance> findAll() {
        return repository.findAll();
    }

    public Balance findById(Long id) { return repository.findById(id).orElse(null);
    }

    public void updateDebitBalance(String balance, String debit, Long id)
    {
       int success = repository.updatenewDebitBalance(balance,debit,id);
    }
    public void updateCreditBalance(String balance, String credit, Long id)
    {
        int success = repository.updatenewCreditBalance(balance,credit,id);
    }
}
