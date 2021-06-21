package com.santander.meetupchallenge.util.exception;

public class UserMeetUpException extends Exception {
    private static final long serialVersionUID = -3608047774876421293L;

    public UserMeetUpException(String msg, Exception e) {
        super(msg, e);
    }

    public UserMeetUpException(String msg) {
        super(msg);
    }
}
