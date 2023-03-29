package com.slashcs.students.exceptions;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException {

    private HttpStatus status;

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, HttpStatus httpStatus) {
        super(message);
        this.status = httpStatus;
    }

    public HttpStatus getStatus () {
        return this.status;
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
