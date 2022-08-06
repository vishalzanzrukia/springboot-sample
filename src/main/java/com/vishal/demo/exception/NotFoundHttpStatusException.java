package com.vishal.demo.exception;

import org.springframework.http.HttpStatus;

/**
 * It's app specific custom exception to use whenever we have to return 404
 *
 * @see com.vishal.demo.resources.GenericExceptionHandler for more details
 */
public class NotFoundHttpStatusException extends AbstractHttpStatusException {

    public NotFoundHttpStatusException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
