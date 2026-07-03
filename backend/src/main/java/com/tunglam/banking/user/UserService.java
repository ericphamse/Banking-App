package com.tunglam.banking.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tunglam.banking.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder; 
import com.tunglam.banking.user.dto.DashBoardResponse;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public DashBoardResponse dashBoardResponse() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authenticated user email/name: " + auth.getName());

        String userId = auth.getName();
        Long userIdLong = Long.parseLong(userId);
        System.out.println("Authenticated user ID: " + userIdLong);
        User user = userRepository.findById(userIdLong)
                .orElseThrow(() -> new IllegalArgumentException("User not found1122"));

        String email = user.getEmail();
        Double balance = user.getBalance();

        return new DashBoardResponse(email, balance);
    }
}