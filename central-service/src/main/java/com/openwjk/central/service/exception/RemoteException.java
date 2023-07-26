package com.openwjk.central.service.exception;


public class RemoteException extends CtException {
    public RemoteException(String msg) {
        super(msg);
    }

    public RemoteException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
