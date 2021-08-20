package com.opaywallet.assessment.web;

import com.opaywallet.assessment.helper.ResponseMessage;
import com.opaywallet.assessment.model.Wallet;
import com.opaywallet.assessment.model.WalletTransaction;
import com.opaywallet.assessment.model.dtos.WalletTransactionDTO;
import com.opaywallet.assessment.service.WalletService;
import com.opaywallet.assessment.service.WalletTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wallet/")
public class WalletTransactionController {

    String message = "";

    @Autowired
    private WalletTransactionService walletTransactionService;

    @Autowired
    private WalletService walletService;

    @GetMapping("transactions")
    public List<WalletTransaction> allTransactions(){
        return walletTransactionService.findAll();
    }

    @PostMapping("deposit/{walletId}")
    public ResponseEntity<ResponseMessage>  payIntoAccount(@RequestBody WalletTransactionDTO walletTransactionDTO,
                                                           @PathVariable String walletId) {

        if (walletTransactionDTO.getTransactionAmount() >= 0){
            walletTransactionService.deposit(walletTransactionDTO, walletId);
            message = "Deposit successfully made into the wallet";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        }
        else {
            message = "Unsuccessful! You have entered an invalid amount. Try again!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
        }
    }

    @PostMapping("withdraw/{walletId}")
    public ResponseEntity<ResponseMessage> drawFromAccount(@RequestBody WalletTransactionDTO walletTransactionDTO,
                                                  @PathVariable String walletId) {

        Optional<Wallet> wallet = walletService.findByWalletRefId(walletId);

        if (walletTransactionDTO.getTransactionAmount() >= 0) {
            if (wallet.get().getWalletBalance() >= walletTransactionDTO.getTransactionAmount()) {
                walletTransactionService.withdraw(walletTransactionDTO, walletId);
                message = "Withdrawal successfully made from the wallet";
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            }
            else {
                message = "Unsuccessful! You have insufficient funds in your wallet!";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
            }
        }
        else {
            message = "Unsuccessful! You have entered an invalid amount. Try again!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
        }
    }
}
