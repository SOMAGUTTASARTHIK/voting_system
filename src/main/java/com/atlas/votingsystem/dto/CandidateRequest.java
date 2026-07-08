package com.atlas.votingsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CandidateRequest {

    @NotBlank(message = "Candidate name is required")
    @Size(max = 45, message = "Candidate name must be at most 100 characters")
    private String name;

    @NotBlank(message = "Party name is required")
    @Size(max = 25, message = "Party name must be at most 100 characters")
    private String partyName;

    @NotBlank(message = "Constituency is required")
    @Size(max = 50, message = "Constituency must be at most 100 characters")
    private String constituency;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Size(max = 100, message = "Manifesto must be at most 1000 characters")
    private String manifesto;
}
