package com.opaywallet.assessment.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class WalletTest {

    Wallet wallet;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testThatWalletClassExists() {
        wallet = new Wallet();
        log.info("Object wallet {} exists", wallet);
        assertNotNull(wallet);
    }
}