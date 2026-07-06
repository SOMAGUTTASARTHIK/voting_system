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
public class CandidateResponse {

    private Long id;
    private String name;
    private String partyName;
    private String constituency;
    private LocalDate dateOfBirth;
    private int age;
    private String manifesto;
    private int voteCount;
    private LocalDateTime createdAt;
}
