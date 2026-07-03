package com.atlas.votingsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CandidateRequest {

    @NotBlank(message = "Candidate name is required")
    @Size(max = 100, message = "Candidate name must be at most 100 characters")
    private String name;

    @NotBlank(message = "Party name is required")
    @Size(max = 100, message = "Party name must be at most 100 characters")
    private String partyName;

    @NotBlank(message = "Constituency is required")
    @Size(max = 100, message = "Constituency must be at most 100 characters")
    private String constituency;

    @Size(max = 1000, message = "Manifesto must be at most 1000 characters")
    private String manifesto;

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

    public String getManifesto() {
        return manifesto;
    }

    public void setManifesto(String manifesto) {
        this.manifesto = manifesto;
    }
}
