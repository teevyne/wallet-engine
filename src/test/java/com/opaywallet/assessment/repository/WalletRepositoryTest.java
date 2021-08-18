package com.opaywallet.assessment.repository;

import com.opaywallet.assessment.model.Wallet;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class WalletRepositoryTest {

    Wallet wallet;

    @BeforeEach
    void setUp() {
    }

    @Autowired
    private WalletRepository walletRepository;

    @Test
    @DisplayName("Test create a wallet")
    void testCreateWallet_ThenSaveToDB(){

        assertNull(wallet);
        log.info("Wallet does not exist yet");

        wallet = new Wallet();

        wallet.setId(3L);
        wallet.setWalletId("WID" + RandomString.make(10).toUpperCase());
        wallet.setWalletBalance(0.0);
        wallet.setCustomerPhoneNumber("08094969620");

        log.info("Saving wallet {} into the database", wallet);

        Wallet aNewWallet = walletRepository.save(wallet);

        assertThat(aNewWallet.getId()).isNotNull();
    }

    @Test
    @DisplayName("Wallet Item Exists")
    void findIssueByIdInTheDB(){

        wallet = walletRepository.findById((long) 1).orElse(null);
        assertThat(wallet).isNotNull();
        log.info("Test wallet retrieved from the database ---> " + wallet);
    }
}