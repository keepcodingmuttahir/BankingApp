package com.redmath.bankingapp.user;
import com.redmath.bankingapp.balance.Balance;
import com.redmath.bankingapp.transaction.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetailsDTO {
    private User user;
    private Balance balance;
   // private Transaction transaction;
    private List<Transaction> transactions;


    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }
}
