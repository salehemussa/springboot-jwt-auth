package com.example.ajira.controller;

import com.example.ajira.model.User;
import com.example.ajira.repository.UserRepository;
import com.example.ajira.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

 @PostMapping("/register")
public ResponseEntity<?> registerUser(@RequestBody User user) {
    if (userRepository.findByUsername(user.getUsername()).isPresent()) {
        return ResponseEntity.badRequest().body(Map.of("error", "Username already exists"));
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    // You can choose role manually when registering or set default
    if (user.getRole() == null || user.getRole().isEmpty()) {
        user.setRole("MANAGER"); // Default to MANAGER
    }

    userRepository.save(user);
    return ResponseEntity.ok(Map.of("message", "User registered successfully!"));
}


    // ✅ Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(Map.of(
                "token", token,
                "username", userDetails.getUsername(),
                "roles", userDetails.getAuthorities()
        ));
    }
}
