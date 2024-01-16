package com.example.HMS.Error;


public class AppointmentsNotFoundException extends Exception {
    public AppointmentsNotFoundException() {
        super();
    }

    public AppointmentsNotFoundException(String message) {
        super(message);
    }

    public AppointmentsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppointmentsNotFoundException(Throwable cause) {
        super(cause);
    }

    protected AppointmentsNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
