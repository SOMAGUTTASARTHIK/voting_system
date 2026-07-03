package com.atlas.votingsystem.service;

import com.atlas.votingsystem.dto.LoginRequest;
import com.atlas.votingsystem.dto.LoginResponse;
import com.atlas.votingsystem.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final String ADMIN_ROLE = "ADMIN";

    private final String email;
    private final String password;
    private final String role;

    public AuthService(@Value("${app.auth.email}") String email,
                       @Value("${app.auth.password}") String password,
                       @Value("${app.auth.role}") String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public LoginResponse login(LoginRequest request) {
        if (!ADMIN_ROLE.equalsIgnoreCase(role)
                || !email.equalsIgnoreCase(request.getEmail())
                || !password.equals(request.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
        return new LoginResponse(email, ADMIN_ROLE, "Admin login successful");
    }
}
