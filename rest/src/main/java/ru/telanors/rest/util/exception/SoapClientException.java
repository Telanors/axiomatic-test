package ru.telanors.rest.util.exception;

public class SoapClientException extends Exception {
    public SoapClientException(String message) {
        super(message);
    }

    public SoapClientException(String message, Throwable cause) {
        super(message, cause);
    }
}