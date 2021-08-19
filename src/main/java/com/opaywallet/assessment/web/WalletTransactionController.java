package com.opaywallet.assessment.web;

import com.opaywallet.assessment.model.WalletTransaction;
import com.opaywallet.assessment.service.WalletTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet/")
public class WalletTransactionController {

    @Autowired
    private WalletTransactionService walletTransactionService;

    @GetMapping("transactions")
    public List<WalletTransaction> allTransactions(){
        return walletTransactionService.findAll();
    }

    @PostMapping("deposit/{amount}/{walletId}")
    public ResponseEntity<String> payIntoAccount(@RequestBody WalletTransaction walletTransaction,
                                                 @PathVariable double amount,
                                                 @PathVariable Long walletId) {

        walletTransactionService.deposit(amount, walletId);
        return new ResponseEntity<>("Deposit Successful", HttpStatus.OK);
    }

    @PostMapping("withdraw/{amount}/{walletId}")
    public ResponseEntity<String> drawFromAccount(@RequestBody WalletTransaction walletTransaction,
                                                 @PathVariable double amount,
                                                 @PathVariable Long walletId) {

        walletTransactionService.withdraw(amount, walletId);
        return new ResponseEntity<>("Withdrawal Successful", HttpStatus.OK);
    }

}
