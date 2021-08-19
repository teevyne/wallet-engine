package com.opaywallet.assessment.web;

import com.opaywallet.assessment.model.Wallet;
import com.opaywallet.assessment.model.WalletDTO;
import com.opaywallet.assessment.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wallet/")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("all")
    public List<Wallet> getAllWallets(){
        return walletService.allWallets();
    }

    @PostMapping("create")
    public ResponseEntity<?> createWallet(@RequestBody WalletDTO walletDTO, HttpServletRequest request) {
        System.out.println(walletDTO);
        Wallet wallet = walletService.createWallet(walletDTO, request);
        return new ResponseEntity<>(wallet, HttpStatus.OK);
    }

    @GetMapping("/{walletId}")
    public Wallet getWallet(@PathVariable Long walletId) {
        return walletService.findById(walletId);
    }

    @GetMapping("/byrefid/{walletId}")
    public Optional<Wallet> getWalletByRefId(@PathVariable String walletId) {
        return walletService.findByWalletRefId(walletId);
    }

    @GetMapping("/byphonenumber/{phoneNumber}")
    public Optional<Wallet> getWalletByPhoneNumber(@PathVariable String phoneNumber) {
        return walletService.findByPhoneNumber(phoneNumber);
    }

    @GetMapping("/balance/{walletId}")
    public double getWalletBalance(@PathVariable String walletId) {
        return walletService.getBalance(walletId);
    }

    @PatchMapping("deactivate/{walletId}")
    public ResponseEntity<String> deactivateWallet(@PathVariable String walletId) {
        walletService.deactivateWallet(walletId);
        return new ResponseEntity<>("Wallet has been successfully deactivated", HttpStatus.OK);
    }

    @PatchMapping("activate/{walletId}")
    public ResponseEntity<String> activateWallet(@PathVariable String walletId) {
        walletService.activateWallet(walletId);
        return new ResponseEntity<>("Wallet has been successfully activated", HttpStatus.OK);
    }
}
