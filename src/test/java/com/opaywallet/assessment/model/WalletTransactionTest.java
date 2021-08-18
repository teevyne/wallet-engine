package com.opaywallet.assessment.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class WalletTransactionTest {

    WalletTransaction walletTransaction;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testThatWalletTransactionClassExists() {
        walletTransaction = new WalletTransaction();
        log.info("Object wallet {} exists", walletTransaction);
        assertNotNull(walletTransaction);
    }
}