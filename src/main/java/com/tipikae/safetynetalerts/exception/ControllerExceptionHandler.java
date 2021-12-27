package com.tipikae.safetynetalerts.exception;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handle exceptions from Controller.
 * @author tipikae
 * @version 1.0
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	/**
	 * Handle ValidationException.
	 * @param e a ValidationException.
	 * @return ControllerException
	 */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    ControllerException exceptionHandler(ValidationException e) {
        return new ControllerException(400, e.getMessage());
    }

    /**
     * Handle ConstraintViolationException.
     * @param e a ConstraintViolationException.
     * @return ControllerException
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ControllerException exceptionHandler(ConstraintViolationException e) {
        return new ControllerException(400, e.getMessage());
    }

}
