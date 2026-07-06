package com.atlas.votingsystem.service;

import com.atlas.votingsystem.dto.LoginRequest;
import com.atlas.votingsystem.dto.LoginResponse;
import com.atlas.votingsystem.dto.VoterLoginRequest;
import com.atlas.votingsystem.dto.VoterLoginResponse;
import com.atlas.votingsystem.entity.Voter;
import com.atlas.votingsystem.exception.InvalidCredentialsException;
import com.atlas.votingsystem.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final String ADMIN_ROLE = "ADMIN";
    private static final String VOTER_ROLE = "VOTER";

    private final String email;
    private final String password;
    private final String role;
    private final VoterRepository voterRepository;

    public AuthService(@Value("${app.auth.email}") String email,
                       @Value("${app.auth.password}") String password,
                       @Value("${app.auth.role}") String role,
                       VoterRepository voterRepository) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.voterRepository = voterRepository;
    }

    public LoginResponse login(LoginRequest request) {
        if (!ADMIN_ROLE.equalsIgnoreCase(role)
                || !email.equalsIgnoreCase(request.getEmail())
                || !password.equals(request.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
        return new LoginResponse(email, ADMIN_ROLE, "Admin login successful");
    }

    public VoterLoginResponse voterLogin(VoterLoginRequest request) {
        Voter voter = voterRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid phone number or PAN number"));

        if (!voter.getPanNumber().equalsIgnoreCase(request.getPanNumber())) {
            throw new InvalidCredentialsException("Invalid phone number or PAN number");
        }

        return new VoterLoginResponse(voter.getId(), voter.getName(), VOTER_ROLE, "Voter login successful");
    }
}
