package com.testez.internal.report;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/12/2015
 */
public enum Failure {
    UNKNOWN_EXCEPTION ("Threw exception %s"),
    UNEXPECTED_EXCEPTION ("Threw exception %s but expected one of %s"),
    MISSING_EXCEPTION ("Missing expected exception[s] %s"),;

    private String message;

    Failure(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
