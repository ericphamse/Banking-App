package com.tunglam.banking.user.dto;


public class DashBoardResponse {
    private String email;
    private Double balance;

    public DashBoardResponse(String email, Double balance) {
        this.email = email;
        this.balance = balance;
    }

    public String getEmail() { return email; }
    public Double getBalance() { return balance; }
}