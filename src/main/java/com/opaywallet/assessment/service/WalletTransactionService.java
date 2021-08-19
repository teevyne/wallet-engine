package com.opaywallet.assessment.service;

import com.opaywallet.assessment.model.WalletTransaction;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface WalletTransactionService {

    ResponseEntity<String> deposit(double amount, Long walletId);

    ResponseEntity<String> withdraw(double amount, Long walletId);

    List<WalletTransaction> findAll();
}
