package com.fdar.apress.s2.services;

import com.fdar.apress.s2.domain.Contestant;

public class VotingResult {

    private Contestant contestant;
    private Long numberOfVotes;

    public VotingResult( Contestant contestant, Long numberOfVotes ) {
        this.contestant = contestant;
        this.numberOfVotes = numberOfVotes;
    }

    public Contestant getContestant() {
        return contestant;
    }

    public Long getNumberOfVotes() {
        return numberOfVotes;
    }

}
