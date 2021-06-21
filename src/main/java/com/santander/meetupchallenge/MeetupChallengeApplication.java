package com.santander.meetupchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class MeetupChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetupChallengeApplication.class, args);
	}

}
