package com.santander.meetupchallenge.response.weather;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherDayTemperature {
	
	private float day;
	private float min;
	private float max;
	private float eve;
	private float nigth;
	private float morn;
}
