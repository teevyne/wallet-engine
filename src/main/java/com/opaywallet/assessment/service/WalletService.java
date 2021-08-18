package com.opaywallet.assessment.service;

import com.opaywallet.assessment.model.Wallet;
import com.opaywallet.assessment.model.WalletDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface WalletService {

    Wallet createWallet(WalletDTO walletDTO, HttpServletRequest request);

    void deactivateWallet(Long walletId);

    void activateWallet(Long walletId);

    Wallet findById(Long walletId);

    Wallet findByWalletId(String walletId);

    double getBalance(Long walletId);

    List<Wallet> allWallets();
}
