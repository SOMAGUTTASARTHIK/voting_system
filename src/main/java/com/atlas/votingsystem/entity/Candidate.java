package com.atlas.votingsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
@Table(name = "candidates")
@Getter
@Setter
@NoArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String partyName;

    @Column(nullable = false, length = 100)
    private String constituency;

    @Column
    private LocalDate dateOfBirth;

    @Column(length = 100)
    private String manifesto;

    @Column(nullable = false)
    private int voteCount;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Candidate(String name, String partyName, String constituency, LocalDate dateOfBirth, String manifesto) {
        this.name = name;
        this.partyName = partyName;
        this.constituency = constituency;
        this.dateOfBirth = dateOfBirth;
        this.manifesto = manifesto;
    }

    @PrePersist
    void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    @Transient
    public int getAge() {
        if (dateOfBirth == null) {
            return 0;
        }
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
