package com.alten.trial.services;

import com.alten.trial.dtos.UserRequest;

public interface UserService {
    void createAccount(UserRequest request);
    String authenticate(String email, String password);
}

