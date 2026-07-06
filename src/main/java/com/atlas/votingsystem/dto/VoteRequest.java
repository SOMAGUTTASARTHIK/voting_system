package com.atlas.votingsystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteRequest {

    @NotNull(message = "Voter id is required")
    private Long voterId;
}
