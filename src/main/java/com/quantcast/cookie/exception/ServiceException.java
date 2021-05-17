package com.quantcast.cookie.exception;

public class ServiceException extends Exception {
    public ServiceException(final Throwable throwable) {
        super(throwable);
    }
}
