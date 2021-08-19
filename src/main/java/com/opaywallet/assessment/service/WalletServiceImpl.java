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

        Wallet newWallet = new Wallet();

        newWallet.setCustomerPhoneNumber(walletDTO.getPhoneNumber());
        newWallet.setWalletBalance(0.0);
        newWallet.setWalletId(walletPrefix + RandomString.make(10).toUpperCase());

        log.info("New Wallet successfully created");
        return walletRepository.save(newWallet);
    }

    @Override
    public void deactivateWallet(Long walletId) {
        Optional<Wallet> wallet = Optional.of(walletRepository.findById(walletId).get());

        if (wallet.get().isEnabled()) {
            wallet.get().setEnabled(false);
        }
        walletRepository.save(wallet.get());
    }

    @Override
    public void activateWallet(Long walletId) {
        Optional<Wallet> wallet = Optional.of(walletRepository.findById(walletId).get());

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
    public double getBalance(Long id) {
        Optional<Wallet> wallet = Optional.of(walletRepository.findById(id).get());
        return wallet.get().getWalletBalance();
    }

    @Override
    public List<Wallet> allWallets() {
        return walletRepository.findAll();
    }
}
