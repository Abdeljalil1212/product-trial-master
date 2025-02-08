package com.alten.trial.services.impl;

import com.alten.trial.dtos.UserRequest;
import com.alten.trial.models.User;
import com.alten.trial.repositories.UserRepository;
import com.alten.trial.security.jwt.JwtUtil;
import com.alten.trial.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    public void createAccount(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email déjà créé");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setFirstname(request.getFirstname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
    }

    public String authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Mot de passe incorrect");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
