package com.santander.meetupchallenge.service;

import com.santander.meetupchallenge.domain.Meetup;
import com.santander.meetupchallenge.repository.MeetupRepository;
import com.santander.meetupchallenge.util.exception.MeetupException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MeetupServiceImpl implements  MeetupService{

    @Autowired
    private MeetupRepository meetupRepository;

    @Override
    public Meetup save(Meetup meetup) throws MeetupException {
        if(meetup.getAddress().isEmpty()){
            throw new MeetupException("Datos invalido");
        }
        return meetupRepository.save(meetup);
    }

    @Override
    public Optional<Meetup> findById(Long id) {
        return meetupRepository.findById(id);
    }
}
