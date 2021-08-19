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
        Wallet wallet = walletService.createWallet(walletDTO, request);

        return new ResponseEntity<>(wallet, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Wallet getWallet(@PathVariable Long id) {
        return walletService.findById(id);
    }

    @GetMapping("/balance/{id}")
    public double getWalletBalance(@PathVariable Long id) {
        return walletService.getBalance(id);
    }

    @PatchMapping("deactivate/{id}")
    public ResponseEntity<String> deactivateWallet(@PathVariable Long id) {
        walletService.deactivateWallet(id);
        return new ResponseEntity<>("Wallet has been successfully deactivated", HttpStatus.OK);
    }

    @PatchMapping("activate/{id}")
    public ResponseEntity<String> activateWallet(@PathVariable Long id) {
        walletService.activateWallet(id);
        return new ResponseEntity<>("Wallet has been successfully activated", HttpStatus.OK);
    }
}
