package com.opaywallet.assessment.service;

import com.opaywallet.assessment.model.*;
import com.opaywallet.assessment.model.dtos.WalletTransactionDTO;
import com.opaywallet.assessment.repository.WalletRepository;
import com.opaywallet.assessment.repository.WalletTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WalletTransactionServiceImpl implements WalletTransactionService{

    @Autowired
    private WalletTransactionRepository walletTransactionRepository;

    @Autowired
    private WalletRepository walletRepository;

    public void deposit(WalletTransactionDTO walletTransactionDTO, String walletId) {

        WalletTransaction walletTransaction = new WalletTransaction(
                TransactionType.DEPOSIT.getId(),
                walletTransactionDTO.getTransactionAmount(),
                walletTransactionDTO.getWalletId(),
                new Date());
        WalletTransaction walletTwo = walletTransactionRepository.save(walletTransaction);

        Optional<Wallet> wallet = Optional.ofNullable(walletRepository.findByWalletId(walletId));
        double newBalance = wallet.get().getWalletBalance() + walletTwo.getTransactionAmount();
        wallet.get().setWalletBalance(newBalance);

        Wallet aWallet = wallet.get();
        walletRepository.save(aWallet);
    }

    @Override
    public void withdraw(WalletTransactionDTO walletTransactionDTO, String walletId) {

        WalletTransaction walletTransaction = new WalletTransaction(
                TransactionType.WITHDRAWAL.getId(),
                walletTransactionDTO.getTransactionAmount(),
                walletTransactionDTO.getWalletId(),
                new Date());
        WalletTransaction walletTwo = walletTransactionRepository.save(walletTransaction);

        Optional<Wallet> wallet = Optional.ofNullable(walletRepository.findByWalletId(walletId));
        double newBalance = wallet.get().getWalletBalance() - walletTwo.getTransactionAmount();
        wallet.get().setWalletBalance(newBalance);
        Wallet aWallet = wallet.get();
        walletRepository.save(aWallet);
    }

    @Override
    public List<WalletTransaction> findAll() {
        return walletTransactionRepository.findAll();
    }
}
