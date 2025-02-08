package com.alten.trial.dtos;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String firstname;
    private String email;
    private String password;
}
