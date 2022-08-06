package com.vishal.demo.resources;

import com.vishal.demo.dto.ApiResponse;
import com.vishal.demo.exception.AbstractHttpStatusException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Common exception handler across all controllers
 */
@Slf4j
@RestControllerAdvice
@SuppressWarnings("unused")
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Generic exception handler when any kind of unknown errors occurs
     *
     * @param ex any exception
     * @return the api response to return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse employeeNotFoundHandler(Exception ex) {
        log.error("Unknown error", ex);
        return ApiResponse.builder().success(false).message("There is technical issue with the api, please contact admin").build();
    }

    /***
     * Generic handler to handle all http status specific exceptions. It will return http status retrieved from the exception object field
     *
     * @param ex base exception to handler
     * @return the api response to return
     */
    @ExceptionHandler(AbstractHttpStatusException.class)
    public ResponseEntity<ApiResponse> handleBaseException(AbstractHttpStatusException ex) {
        return new ResponseEntity<>(
                ApiResponse.builder().success(false).message(ex.getMessage()).build(),
                ex.getHttpStatus());
    }

    /***
     * This will handle when we pass different type of value than expected in request params.
     * Example: if you run this API: DELETE /api/client/hello, this will handle because "hello" is invalid value for integer type
     *
     * @param ex the MethodArgumentTypeMismatchException
     * @return the api response to return
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ApiResponse> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String error = String.format(
                "%s should be of type %s, but provided of type %s",
                ex.getName(),
                ex.getRequiredType(),
                ClassUtils.getDescriptiveType(ex.getValue())
        );
        log.error(error);
        return new ResponseEntity<>(
                ApiResponse.builder().success(false).message(error).build(),
                HttpStatus.BAD_REQUEST);
    }
}
