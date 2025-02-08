package com.alten.trial.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_NAME",nullable = false)
    private String username;

    @Column(name="FIRST_NAME",nullable = false)
    private String firstname;

    @Column(name = "E_MAIL",nullable = false)
    private String email;

    @Column(name = "PASSWORD" ,nullable = false)
    private String password;
}

