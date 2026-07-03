package com.atlas.votingsystem.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public CandidateResponse() {
    }

    public CandidateResponse(Long id, String name, String partyName, String constituency, LocalDate dateOfBirth,
                             int age, String manifesto, int voteCount, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.partyName = partyName;
        this.constituency = constituency;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.manifesto = manifesto;
        this.voteCount = voteCount;
        this.createdAt = createdAt;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPartyName() {

        return partyName;
    }

    public void setPartyName(String partyName) {

        this.partyName = partyName;
    }

    public String getConstituency() {

        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getManifesto() {
        return manifesto;
    }

    public void setManifesto(String manifesto) {
        this.manifesto = manifesto;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
