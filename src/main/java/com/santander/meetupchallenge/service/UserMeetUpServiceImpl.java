package com.santander.meetupchallenge.service;

import com.santander.meetupchallenge.cache.TemperatureCache;
import com.santander.meetupchallenge.domain.Meetup;
import com.santander.meetupchallenge.domain.UserMeetup;
import com.santander.meetupchallenge.repository.UserMeetupRepository;
import com.santander.meetupchallenge.response.MeetUpObtainTemperatureResponse;
import com.santander.meetupchallenge.response.weather.WeatherDay;
import com.santander.meetupchallenge.response.weather.WeatherResponse;
import com.santander.meetupchallenge.util.exception.MeetupException;
import com.santander.meetupchallenge.util.exception.UserException;
import com.santander.meetupchallenge.util.exception.UserMeetUpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMeetUpServiceImpl implements UserMeetupService {
    @Autowired
    private UserMeetupRepository userMeetupRepository;

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private MeetupService meetupService;

    @Autowired
    private TemperatureCache temperatureCache;

    @Override
    public UserMeetup save(Long userId, Long meetupId) throws UserException, MeetupException, UserMeetUpException {
        if (!userService.findById(userId).isPresent()) {
            throw new UserException("No existe el usuario");
        }
        if (!meetupService.findById(meetupId).isPresent()) {
            throw new MeetupException("No existe la meetup");
        }
        if (userMeetupRepository.findByUserAndMeetup(userId, meetupId).isPresent()) {
            throw new UserMeetUpException("Ya te encuentras registrado en la meetup");
        }
        UserMeetup userMeetup = new UserMeetup();
        userMeetup.setMeetupId(meetupId);
        userMeetup.setUserId(userId);
        userMeetup.setAssistance(false);
        return userMeetupRepository.save(userMeetup);
    }

    @Override
    public void registerUser(Long userId, Long meetupId, boolean assistance) throws UserMeetUpException {

        if (!userMeetupRepository.findByUserAndMeetup(userId, meetupId).isPresent()) {
            throw new UserMeetUpException("El usuario no esta registrado en la meetup o la meetup no existe");
        }

        userMeetupRepository.updateAssistance(userId, meetupId, assistance);

    }
    @Override
    public MeetUpObtainTemperatureResponse getTemperature(Long meetUpId) throws Exception {
        MeetUpObtainTemperatureResponse meetUpObtainTemperatureResponse = new MeetUpObtainTemperatureResponse();
        if(!meetupService.findById(meetUpId).isPresent()) {
            throw new MeetupException("No existe la meetUp");
        }

        Meetup meetUp = meetupService.findById(meetUpId).get();
        meetUpObtainTemperatureResponse.setTemperature(getTemperatureOfMeetUp(meetUp));
        return meetUpObtainTemperatureResponse;
    }

    @Override
    public Double getAmountBeer(Long meetUpId) throws Exception {
        MeetUpObtainTemperatureResponse temperature = getTemperature(meetUpId);
        List <UserMeetup> meetups = userMeetupRepository.findAllByMeetupId(meetUpId);
        Double beerAmount;
        if (temperature.getTemperature() < 20){
            beerAmount = meetups.size() * 0.75;

        }else if (temperature.getTemperature() > 24){
            beerAmount = Double.valueOf(meetups.size() * 2);

        }else {

            beerAmount = Double.valueOf(meetups.size() * 1);
        }

        Double boxesOfBeer = Math.ceil(beerAmount/6);

        return boxesOfBeer;
    }


    private Double getTemperatureOfMeetUp(Meetup meetUp) throws Exception {
        WeatherResponse weatherResponse = temperatureCache.getTemperature();
        Double temp = null;
        for (WeatherDay weatherDay : weatherResponse.getList()) {
            if (meetUp.getDate().compareTo(weatherDay.getDate()) == 0) {
                temp = (double) weatherDay.getTemp().getMax();
            }
        }
        return (double)Math.round(temp);
    }






}
