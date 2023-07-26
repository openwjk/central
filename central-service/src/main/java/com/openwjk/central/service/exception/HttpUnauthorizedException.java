package com.openwjk.central.service.exception;


public class HttpUnauthorizedException extends CtException {
    public HttpUnauthorizedException() {
        super("receive httpStatusCode 401");
    }

    public HttpUnauthorizedException(String msg, Throwable cause) {
        super("receive httpStatusCode 401, " + msg, cause);
    }
}
