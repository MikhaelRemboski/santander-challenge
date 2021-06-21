package com.santander.meetupchallenge.service;

import com.santander.meetupchallenge.domain.Meetup;
import com.santander.meetupchallenge.util.exception.MeetupException;
import java.util.Optional;


public interface MeetupService {
    Meetup save(Meetup meetup) throws MeetupException;
    Optional<Meetup> findById(Long id);

}
