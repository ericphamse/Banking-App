package com.tunglam.banking.bank_account.dto;

import com.tunglam.banking.bank_account.AccountType;

public class BankAccountRequest {
    private AccountType accountType;

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}