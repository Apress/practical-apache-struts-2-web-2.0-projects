package com.fdar.apress.s2.services;

import com.fdar.apress.s2.domain.User;
import com.fdar.apress.s2.services.VotingResult;

import java.util.List;

/**
 * @author Ian Roughley
 * @version $Id$
 */
public interface VotingService {

    void enroll(User user, Long eventId);

    void vote(User user, Long eventId, Long contestantId);

    Long getResults(Long eventId, Long contestantId);
}
