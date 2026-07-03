package com.atlas.votingsystem.service;

import com.atlas.votingsystem.dto.LoginRequest;
import com.atlas.votingsystem.dto.LoginResponse;
import com.atlas.votingsystem.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final String ADMIN_ROLE = "ADMIN";

    private final String username;
    private final String password;
    private final String role;

    public AuthService(@Value("${app.auth.username}") String username,
                       @Value("${app.auth.password}") String password,
                       @Value("${app.auth.role}") String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public LoginResponse login(LoginRequest request) {
        if (!ADMIN_ROLE.equalsIgnoreCase(role)
                || !username.equals(request.getUsername())
                || !password.equals(request.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }
        return new LoginResponse(username, ADMIN_ROLE, "Admin login successful");
    }
}
