package com.opaywallet.assessment.service;

import com.opaywallet.assessment.model.WalletTransaction;
import com.opaywallet.assessment.model.dtos.WalletTransactionDTO;

import java.util.List;

public interface WalletTransactionService {

    void deposit(WalletTransactionDTO walletTransactionDTO, String walletId);

    void withdraw(WalletTransactionDTO walletTransactionDTO, String walletId);

    List<WalletTransaction> findAll();
}
