package com.tunglam.banking.bank_account;

import com.tunglam.banking.user.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "account_number", unique = true, nullable = true)
    private String accountNumber;   // e.g. "1234 5678 9012"

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    @Column(nullable = false)
    private Double balance = 0.0;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    public Long getId() { return id; }
    
    public User getUser() { return user; }
  
    public String getAccountNumber() { return accountNumber; }

    public AccountType getAccountType() { return accountType; }

    public Double getBalance() { return balance; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }

    public void setUser(User user) { this.user = user; }

    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public void setAccountType(AccountType accountType) { this.accountType = accountType; }

    public void setBalance(Double balance) { this.balance = balance; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }    
}