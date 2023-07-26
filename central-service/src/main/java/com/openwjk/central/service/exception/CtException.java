package com.openwjk.central.service.exception;




public class CtException extends RuntimeException {
    public CtException(String message) {
        super(message);
    }

    public CtException(String message, Throwable cause) {
        super(message, cause);
    }

}
