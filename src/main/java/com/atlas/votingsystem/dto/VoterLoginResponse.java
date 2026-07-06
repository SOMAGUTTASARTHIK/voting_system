package com.atlas.votingsystem.dto;

public class VoterLoginResponse {

    private Long voterId;
    private String name;
    private String role;
    private String message;

    public VoterLoginResponse() {
    }

    public VoterLoginResponse(Long voterId, String name, String role, String message) {
        this.voterId = voterId;
        this.name = name;
        this.role = role;
        this.message = message;
    }

    public Long getVoterId() {
        return voterId;
    }

    public void setVoterId(Long voterId) {
        this.voterId = voterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
