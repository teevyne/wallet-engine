package com.opaywallet.assessment.web;

import com.opaywallet.assessment.helper.ResponseMessage;
import com.opaywallet.assessment.model.Wallet;
import com.opaywallet.assessment.model.dtos.*;
import com.opaywallet.assessment.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wallet/")
@Slf4j
public class WalletController {

    String message = "";

    @Autowired
    private WalletService walletService;

    @GetMapping("all")
    public List<Wallet> getAllWallets(){
        return walletService.allWallets();
    }

    @PostMapping("create")
    public ResponseEntity<ResponseMessage>  createWallet(@RequestBody WalletDTO walletDTO, HttpServletRequest request) {

        Wallet wallet = walletService.findByPhoneNumber(walletDTO.getPhoneNumber());

        if (wallet == null){
            walletService.createWallet(walletDTO, request);
            message = "New Wallet successfully created for " + walletDTO.getPhoneNumber();
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
        }
        else {
            message = "Unsuccessful! An account already exists with the phone number " + walletDTO.getPhoneNumber();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/{walletId}")
    public Wallet getWallet(@PathVariable Long walletId) {
        return walletService.findById(walletId);
    }

    @GetMapping("/byrefid")
    public Optional<Wallet> getWalletByRefId(@RequestBody WalletBalanceDTO walletBalanceDTO) {
        return walletService.findByWalletRefId(walletBalanceDTO.getWalletId());
    }

    @GetMapping("byphonenumber")
    public Wallet getWalletByPhoneNumber(@RequestBody WalletQueryDTO walletDTO) {
        return walletService.findByPhoneNumber(walletDTO.getPhoneNumber());
    }

    @GetMapping("/balance")
    public ResponseEntity<ResponseMessage>  getWalletBalance(@RequestBody WalletBalanceDTO walletBalanceDTO) {
        message = "Your current wallet balance is " + walletService.getBalance(walletBalanceDTO.getWalletId());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }

    @GetMapping("/deactivate")
    public ResponseEntity<ResponseMessage> deactivateWallet(@RequestBody DeactivateWalletDTO deactivateWalletDTO) {
        Optional<Wallet> wallet = walletService.findByWalletRefId(deactivateWalletDTO.getWalletId());

        if (!wallet.get().isEnabled()){
            message = "Sorry! This wallet is already inactive";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
        }
        else {
            walletService.deactivateWallet(deactivateWalletDTO.getWalletId());
            message = "Wallet has been deactivated";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/activate")
    public ResponseEntity<ResponseMessage>  activateWallet(@RequestBody ActivateWalletDTO activateWalletDTO) {

        Optional<Wallet> wallet = walletService.findByWalletRefId(activateWalletDTO.getWalletId());

        if (wallet.get().isEnabled()){
            message = "Sorry! This wallet is already active";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
        }
        else {
            walletService.activateWallet(activateWalletDTO.getWalletId());
            message = "Wallet has been activated";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        }
    }
}
