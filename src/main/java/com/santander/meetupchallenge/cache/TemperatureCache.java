package com.santander.meetupchallenge.cache;

import com.santander.meetupchallenge.response.weather.WeatherResponse;
import com.santander.meetupchallenge.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;

@Transactional
@Component
public class TemperatureCache {
	
	@Autowired
	private WeatherService weatherService;
	
	@Cacheable("temperatureDay")
	public WeatherResponse getTemperature() throws Exception {
		return weatherService.getWeather();
		
	}
	
	@CachePut(value = "temperatureDay")
	public WeatherResponse refresh() throws Exception {
		return getTemperature();
	}
}
