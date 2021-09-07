package com.santander.meetupchallenge.util.exception;

public class MeetupException extends Exception{
    private static final long serialVersionUID = -3608047774876421293L;

    public MeetupException(String msg, Exception e) {
        super(msg, e);
    }

    public MeetupException(String msg) {
        super(msg);
    }
}
