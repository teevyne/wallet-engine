package com.opaywallet.assessment.service;

import com.opaywallet.assessment.model.Wallet;
import com.opaywallet.assessment.model.WalletDTO;
import com.opaywallet.assessment.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    String walletPrefix = "WID";

    @Override
    public Wallet createWallet(WalletDTO walletDTO, HttpServletRequest request) {

        Wallet wallet = walletRepository.findByCustomerPhoneNumber(walletDTO.getPhoneNumber());

        if (wallet != null) {
            // tell them a wallet with the phone number already exists;
            return null;
        }
        else {

            Wallet newWallet = new Wallet();
            System.out.println(walletDTO.getPhoneNumber());

            newWallet.setCustomerPhoneNumber(walletDTO.getPhoneNumber());
            newWallet.setWalletBalance(0.0);
            newWallet.setWalletId(walletPrefix + RandomString.make(10).toUpperCase());

            log.info("New Wallet successfully created");
            return walletRepository.save(newWallet);
        }
    }

    @Override
    public void deactivateWallet(String walletId) {
        Optional<Wallet> wallet = Optional.ofNullable(walletRepository.findByWalletId(walletId));

        if (wallet.get().isEnabled()) {
            wallet.get().setEnabled(false);
        }
        walletRepository.save(wallet.get());
    }

    @Override
    public void activateWallet(String walletId) {
        Optional<Wallet> wallet = Optional.ofNullable(walletRepository.findByWalletId(walletId));

        if (!wallet.get().isEnabled()) {
            wallet.get().setEnabled(true);
        }
        walletRepository.save(wallet.get());
    }

    @Override
    public Wallet findById(Long walletId) {
        return walletRepository.findById(walletId).orElse(null);
    }

    @Override
    public double getBalance(String id) {
        Optional<Wallet> wallet = Optional.of(walletRepository.findByWalletId(id));
        return wallet.get().getWalletBalance();
    }

    @Override
    public List<Wallet> allWallets() {
        return walletRepository.findAll();
    }

    @Override
    public Optional<Wallet> findByWalletRefId(String walletId) {
        return Optional.of(walletRepository.findByWalletId(walletId));
    }

    @Override
    public Optional<Wallet> findByPhoneNumber(String phoneNumber) {
        return Optional.of(walletRepository.findByCustomerPhoneNumber(phoneNumber));
    }

    public Wallet getAnotherWallet(String phoneNumber) {
        return walletRepository.findByCustomerPhoneNumber(phoneNumber);
    }
}
