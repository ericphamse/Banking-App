package com.tunglam.banking.bank_account;

import com.tunglam.banking.bank_account.dto.BankAccountRequest;
import com.tunglam.banking.user.User;
import com.tunglam.banking.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository, UserRepository userRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
    }

    public List<BankAccount> getAccountsByUser(String userIdStr) {
        Long userId = Long.parseLong(userIdStr);
        return bankAccountRepository.findByUserId(userId);
    }

    public BankAccount createAccount(String userIdStr, BankAccountRequest dto) {
        Long userId = Long.parseLong(userIdStr);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BankAccount account = new BankAccount();
        account.setUser(user);
        account.setAccountType(dto.getAccountType());
        account.setBalance(0.0);
        account.setAccountNumber(null);   // admin fills later
        account.setCreatedAt(LocalDateTime.now());

        return bankAccountRepository.save(account);
    }
}