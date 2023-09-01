package com.redmath.bankingapp.balance;

import com.redmath.bankingapp.transaction.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepository extends JpaRepository < Balance, Long >{

    @Transactional
    @Modifying
    @Query(value = "UPDATE balance SET Amount = ?1, debit = ?2 WHERE id = ?3", nativeQuery = true)
    int updatenewDebitBalance(String balance,String debit, Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE balance SET Amount = ?1, credit = ?2 WHERE id = ?3", nativeQuery = true)
    int updatenewCreditBalance(String balance,String credit, Long id);
}
