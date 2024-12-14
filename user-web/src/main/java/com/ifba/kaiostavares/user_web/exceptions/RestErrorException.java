package com.ifba.kaiostavares.user_web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class RestErrorException extends RuntimeException {
    private final HttpStatusCode status;

    public RestErrorException(HttpStatusCode status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatusCode getStatus() {
        return status;
    }
}
