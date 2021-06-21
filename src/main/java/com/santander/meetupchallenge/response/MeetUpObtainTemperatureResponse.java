package com.santander.meetupchallenge.response;

public class MeetUpObtainTemperatureResponse {
	
	private Double temperature;

	public MeetUpObtainTemperatureResponse() {
		super();
	}

	public MeetUpObtainTemperatureResponse(Double temperature) {
		super();
		this.temperature = temperature;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	
	

}
