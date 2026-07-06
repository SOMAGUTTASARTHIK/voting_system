package com.atlas.votingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoterResponse {

    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String aadharNo;
    private String panNumber;
    private int age;
    private String role;
    private LocalDateTime createdAt;
}
