package com.tunglam.banking.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginResponse {
    private String accessToken;
    private String fullName;

    public LoginResponse(String accessToken, String fullName) {
        this.accessToken = accessToken;
        this.fullName = fullName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getFullName() {
        return fullName;
    }
}