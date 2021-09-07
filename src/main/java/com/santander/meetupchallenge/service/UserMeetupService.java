package com.santander.meetupchallenge.service;

import com.santander.meetupchallenge.domain.UserMeetup;
import com.santander.meetupchallenge.response.MeetUpObtainTemperatureResponse;
import com.santander.meetupchallenge.util.exception.MeetupException;
import com.santander.meetupchallenge.util.exception.UserException;
import com.santander.meetupchallenge.util.exception.UserMeetUpException;


public interface UserMeetupService {
    UserMeetup save(Long userId, Long meetupId) throws UserException, MeetupException, UserMeetUpException;

    void registerUser(Long userId, Long meetupId, boolean assistance) throws UserMeetUpException;

    MeetUpObtainTemperatureResponse getTemperature(Long meetUpId) throws Exception;

    Double getAmountBeer(Long meetUpId) throws Exception;
}
