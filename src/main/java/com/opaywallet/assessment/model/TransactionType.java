package com.opaywallet.assessment.model;

public enum TransactionType {

    DEPOSIT(1),
    WITHDRAWAL(2);

    int id;

    TransactionType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
