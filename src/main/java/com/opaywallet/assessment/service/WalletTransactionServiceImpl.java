package com.opaywallet.assessment.service;

import com.opaywallet.assessment.model.CustomerTransaction;
import com.opaywallet.assessment.model.TransactionType;
import com.opaywallet.assessment.model.Wallet;
import com.opaywallet.assessment.model.WalletTransaction;
import com.opaywallet.assessment.repository.WalletRepository;
import com.opaywallet.assessment.repository.WalletTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    CustomerTransaction customerTransaction = new CustomerTransaction();

    public ResponseEntity<String> deposit(double amount, Long walletId) {

        WalletTransaction walletTransaction = new WalletTransaction(TransactionType.DEPOSIT.getId(), amount, new Date());
        walletTransactionRepository.save(walletTransaction);

        Optional<Wallet> wallet = walletRepository.findById(walletId);
        double newBalance = wallet.get().getWalletBalance() + amount;
        wallet.get().setWalletBalance(newBalance);

        Wallet aWallet = wallet.get();
        walletRepository.save(aWallet);

        return new ResponseEntity<>("Deposit successfully made into the account", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> withdraw(double amount, Long walletId) {

        WalletTransaction walletTransaction = new WalletTransaction(TransactionType.WITHDRAWAL.getId(), amount, new Date());
        walletTransactionRepository.save(walletTransaction);

        Optional<Wallet> wallet = walletRepository.findById(walletId);
        if (wallet.isPresent()) {
            if (wallet.get().getWalletBalance() >= amount) {
                double newBalance = wallet.get().getWalletBalance() - amount;
                wallet.get().setWalletBalance(newBalance);
                Wallet aWallet = wallet.get();
                walletRepository.save(aWallet);
                return new ResponseEntity<>("Withdrawal successfully made from the account", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Insufficient funds to withdraw", HttpStatus.FORBIDDEN);
            }
        }
        else{
            return new ResponseEntity<>("An account with the ID cannot be found", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<WalletTransaction> findAll() {
        return walletTransactionRepository.findAll();
    }

    @Override
    public WalletTransaction findById(Long transactionID) {
        return walletTransactionRepository.findById(transactionID).orElse(null);
    }
}
