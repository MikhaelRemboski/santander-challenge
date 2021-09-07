package com.santander.meetupchallenge.service;


import com.santander.meetupchallenge.response.weather.WeatherResponse;
import java.net.URI;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {


    private static final String WEATHER_URL = "https://community-open-weather-map.p.rapidapi.com/forecast/daily?q=Argentina&units=metric";
    private static final String HOST = "community-open-weather-map.p.rapidapi.com";
    private static final String KEY = "7045682226msh139cb0b3c070f6cp1aca19jsnc1fedea4e257";




    protected static final HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-rapidapi-host", HOST);
        headers.set("x-rapidapi-key", KEY);
        return headers;
    }

    public WeatherResponse getWeather() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(null, getHeaders());
        ResponseEntity<WeatherResponse> result = restTemplate.exchange(URI.create(WEATHER_URL),HttpMethod.GET, request, WeatherResponse.class);
        System.out.println(result.toString());
        return result.getBody();
    }
}