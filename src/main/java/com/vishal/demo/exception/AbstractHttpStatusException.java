package com.vishal.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/***
 * Base app specific custom exception bound with http status. <BR>
 * Http status will be used from exception handler to prepare the API response.
 */
@Getter
public abstract class AbstractHttpStatusException extends RuntimeException {

    protected HttpStatus httpStatus;

    public AbstractHttpStatusException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
