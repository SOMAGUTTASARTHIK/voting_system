package com.atlas.votingsystem.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public VoterResponse() {
    }

    public VoterResponse(Long id, String name, LocalDate dateOfBirth, String phoneNumber, String aadharNo,
                         String panNumber, int age, String role, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.aadharNo = aadharNo;
        this.panNumber = panNumber;
        this.age = age;
        this.role = role;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
