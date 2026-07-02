package com.tunglam.banking.auth;

import com.tunglam.banking.user.User;
import com.tunglam.banking.user.UserRepository;
import com.tunglam.banking.auth.JwtService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest dto) {
        String email = dto.getEmail();

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email is already taken");
        }

        String fullname = dto.getFullName();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(dto.getPassword());

        User newUser = new User();
        newUser.setFullName(fullname);
        newUser.setEmail(email);
        newUser.setPasswordHash(hashedPassword);

        userRepository.save(newUser);
    }

    public LoginResponse login(LoginRequest dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        System.out.println("FOUND USER: " + user.getEmail());
        System.out.println("HASH: " + user.getPasswordHash());
        System.out.println("HASH: " + user.getFullName());

        String token = jwtService.generateToken(user);
        String fullName = user.getFullName();
        System.out.println("FULL NAME: " + fullName);
        System.out.println("Token: " + token);

        return new LoginResponse(token, fullName);
        // Authentication successful, you can generate a token or set up a session here
    }
}