package com.santander.meetupchallenge.response.weather;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
@Getter
@Setter
public class WeatherDay {
	
	private static final int SECONDS_TO_MILIS = 1000;
	
	private long dt;
	private long sunrise;
	private long sunset;
	private WeatherDayTemperature temp;

	public LocalDate getDate() {
		return Instant.ofEpochMilli(dt * SECONDS_TO_MILIS).atZone(ZoneId.systemDefault()).toLocalDate();
	}
	

}
