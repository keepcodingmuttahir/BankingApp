package com.redmath.bankingapp.transaction;

import com.redmath.bankingapp.balance.Balance;
import com.redmath.bankingapp.balance.BalanceRepository;
import com.redmath.bankingapp.balance.BalanceService;
import com.redmath.bankingapp.user.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository repository;
    private final BalanceService balanceService;

    public TransactionService(TransactionRepository repository, BalanceService balanceService) {
        this.repository = repository;
        this.balanceService = balanceService;

    }

    public List<Transaction> findAll() {
        return repository.findAll();
    }

    public Transaction create(Transaction transaction)
    {
        //transaction.setUser_id(transaction.getUser_id());
        transaction.setDate(LocalDateTime.now());
        long User_id = transaction.getUser_id();
        Balance balance = balanceService.findById(User_id);
        long credit = Integer.parseInt(balance.getCredit());
        long debit = Integer.parseInt(balance.getDebit());
        long BalanceAmount = Integer.parseInt(balance.getAmount());
        long TransactionAmount = Integer.parseInt(transaction.getAmount());
        if(transaction.getTransType().equals("debit") && BalanceAmount >= TransactionAmount)
        {
            BalanceAmount = BalanceAmount - TransactionAmount;
            debit = debit - TransactionAmount;
            String newdebit = Integer.toString((int) debit);
            String newBalance = Integer.toString((int) BalanceAmount);
            balanceService.updateDebitBalance(newBalance,newdebit, balance.getId());
        } else if (transaction.getTransType().equals("credit"))
        {
            BalanceAmount = BalanceAmount + TransactionAmount;
            credit = credit + TransactionAmount;
            String newcredit = Integer.toString((int) credit);
            String newBalance = Integer.toString((int) BalanceAmount);
            balanceService.updateCreditBalance(newBalance,newcredit, balance.getId());
        }
        return repository.save(transaction);

    }

    public List<Transaction> findByUserId(Long userId) {
        return repository.findByUser_Id(userId);
    }

}
