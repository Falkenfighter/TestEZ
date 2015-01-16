package com.testez.internal.exception;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/16/2015
 */
public class TestEZException extends Exception {
    public TestEZException() {
        super();
    }

    public TestEZException(String message) {
        super(message);
    }

    public TestEZException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestEZException(Throwable cause) {
        super(cause);
    }

    protected TestEZException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
