package com.tunglam.banking.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;   
import org.springframework.security.core.context.SecurityContextHolder; 
import java.util.Map;
import com.tunglam.banking.user.dto.DashBoardResponse;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //@GetMapping("/me")
    //public ResponseEntity<?> getMe(Authentication auth) {
        //return ResponseEntity.ok().body(Map.of("user", auth.getEmail(), "authorities", auth.getAuthorities()));
    //}

    @GetMapping("/getBalance")
    public ResponseEntity<?> dashBoardResponse() {
        System.out.println("Received request to get balance");
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            System.out.println("AUTH = " + auth);
            System.out.println("NAME = " + (auth != null ? auth.getName() : "null"));

            DashBoardResponse dashBoardResponse = userService.dashBoardResponse();

            System.out.println("User " + dashBoardResponse.getEmail() + " balance: " + dashBoardResponse.getBalance());
            return ResponseEntity.ok(dashBoardResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}