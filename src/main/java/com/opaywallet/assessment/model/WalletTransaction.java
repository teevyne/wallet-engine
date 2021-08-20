package com.opaywallet.assessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int transactionType;

    private double transactionAmount;

    private String walletId;

    private Date transactionDate;

    public WalletTransaction(int transactionType, double transactionAmount, String walletId, Date transactionDate) {
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.walletId = walletId;
    }
}
