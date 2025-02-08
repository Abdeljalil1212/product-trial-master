package com.alten.trial.controllers;

import com.alten.trial.dtos.LoginRequest;
import com.alten.trial.dtos.UserRequest;
import com.alten.trial.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/account")
    public ResponseEntity<String> createAccount(@RequestBody UserRequest request) {
        userService.createAccount(request);
        return ResponseEntity.ok("Compte créé avec succès");
    }

    @PostMapping("/token")
    public ResponseEntity<String> authenticate(@RequestBody LoginRequest request) {
        String token = userService.authenticate(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(token);
    }
}

