package com.opaywallet.assessment.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletTransactionDTO {

    private int transactionType;

    private double transactionAmount;

    private String walletId;

//    private Date transactionDate;
}
