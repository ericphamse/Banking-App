package com.tunglam.banking.bank_account;

import com.tunglam.banking.bank_account.dto.BankAccountRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/bank-account")
@CrossOrigin(origins = "http://localhost:3000")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/getAccountDetails")
    public ResponseEntity<?> getAccountDetails() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userId = auth.getName();
            Long userIdLong = Long.parseLong(userId);
            List<BankAccount> accounts = bankAccountService.getAccountsByUser(userId);
            return ResponseEntity.ok(accounts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**@PostMapping("/create")
    public ResponseEntity<?> createBankAccount(@RequestBody BankAccountRequest dto) {
        try {
            bankAccountService.createBankAccount(dto);
            return ResponseEntity.status(201).body("Bank account created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }**/

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody BankAccountRequest dto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userId = auth.getName();
            BankAccount account = bankAccountService.createAccount(userId, dto);
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}