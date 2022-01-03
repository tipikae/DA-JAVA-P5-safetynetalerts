package com.tipikae.safetynetalerts.exception;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Handle exceptions from Controller.
 * @author tipikae
 * @version 1.0
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	
	/**
	 * Handle ServiceException.
	 * @param e a ServiceException.
	 * @return ControllerException
	 */
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ServiceException.class)
	ControllerException exceptionHandler(ServiceException e) {
		logException(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return new ControllerException(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}
	
	/**
	 * Handle StorageException.
	 * @param e a StorageException.
	 * @return ControllerException
	 */
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(StorageException.class)
	ControllerException exceptionHandler(StorageException e) {
		logException(HttpStatus.CONFLICT.value(), e.getMessage());
		return new ControllerException(HttpStatus.CONFLICT.value(), e.getMessage());
	}
	
	/**
	 * Handle ConverterException.
	 * @param e a ConverterException.
	 * @return ControllerException
	 */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConverterException.class)
	ControllerException exceptionHandler(ConverterException e) {
		logException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		return new ControllerException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}

	/**
	 * Handle ValidationException.
	 * @param e a ValidationException.
	 * @return ControllerException
	 */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    ControllerException exceptionHandler(ValidationException e) {
		logException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ControllerException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
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
    	StringBuilder errors = new StringBuilder();
        e.getConstraintViolations().forEach((violation) -> {
        	errors.append(violation.getPropertyPath() + ": " + violation.getMessage() + ", ");
        });
		logException(HttpStatus.BAD_REQUEST.value(), errors.toString());
        return new ControllerException(HttpStatus.BAD_REQUEST.value(), errors.toString());
    }

    /**
     * Handle MethodArgumentNotValidException.
     * @param e a MethodArgumentNotValidException.
     * @return ControllerException
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ControllerException exceptionHandler(MethodArgumentNotValidException e) {
    	StringBuilder errors = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.append(fieldName + ": " + errorMessage + ", ");
        });
		logException(HttpStatus.BAD_REQUEST.value(), errors.toString());
        return new ControllerException(HttpStatus.BAD_REQUEST.value(), errors.toString());
    }

    /**
     * Handle MethodArgumentNotValidException.
     * @param e a MethodArgumentNotValidException.
     * @return ControllerException
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ControllerException exceptionHandler(MethodArgumentTypeMismatchException e) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("There is a problem with the parameter: " + e.getName());
		logException(HttpStatus.BAD_REQUEST.value(), sb.toString());
    	return new ControllerException(HttpStatus.BAD_REQUEST.value(), sb.toString());
    }

	private void logException(int code, String message) {
		LOGGER.error("Error code: {}, {}", code, message);
	}
}
