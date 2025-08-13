package com.ttd.microsoftlistsunittest.exception;

public class MsListRuntimeException extends RuntimeException {
    public MsListRuntimeException(String message) {
        super(message);
    }

    public MsListRuntimeException(Throwable cause) {
        super(cause);
    }
}
