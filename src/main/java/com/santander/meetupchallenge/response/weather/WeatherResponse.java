package com.santander.meetupchallenge.response.weather;



import java.util.List;

public class WeatherResponse {
    private List<WeatherDay> list;

    public List<WeatherDay> getList() {
        return list;
    }

    public void setList(List<WeatherDay> list) {
        this.list = list;
    }

}
