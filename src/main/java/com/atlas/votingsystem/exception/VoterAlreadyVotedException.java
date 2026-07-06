package com.atlas.votingsystem.exception;

public class VoterAlreadyVotedException extends RuntimeException {

    public VoterAlreadyVotedException(String message) {
        super(message);
    }
}
