package com.stackroute.Muzix.Exceptions;

public class TrackNotFound extends Exception{
    private  String message;
    public TrackNotFound(String message) {
        super(message);
        this.message=message;
    }

    public TrackNotFound() {
    }

}

