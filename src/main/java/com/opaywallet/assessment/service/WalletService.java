package com.opaywallet.assessment.service;

import com.opaywallet.assessment.model.Wallet;
import com.opaywallet.assessment.model.dtos.WalletBalanceDTO;
import com.opaywallet.assessment.model.dtos.WalletDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface WalletService {

    void createWallet(WalletDTO walletDTO, HttpServletRequest request);

    void deactivateWallet(String walletId);

    void activateWallet(String walletId);

    Wallet findById(Long walletId);

    double getBalance(String walletId);

    List<Wallet> allWallets();

    Optional<Wallet> findByWalletRefId(String walletId);

    Wallet findByPhoneNumber(String phoneNumber);
}
