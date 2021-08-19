package com.opaywallet.assessment.service;

import com.opaywallet.assessment.model.WalletTransaction;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface WalletTransactionService {

    ResponseEntity<String> deposit(double amount, String walletId);

    ResponseEntity<String> withdraw(double amount, String walletId);

    List<WalletTransaction> findAll();
}
