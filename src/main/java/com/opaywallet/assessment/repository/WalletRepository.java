package com.opaywallet.assessment.repository;

import com.opaywallet.assessment.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findByWalletId(String walletId);

    Wallet findByCustomerPhoneNumber(String phoneNumber);
}
