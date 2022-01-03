package com.tipikae.safetynetalerts.exception;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

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
	
	/**
	 * Handle ServiceException.
	 * @param e a ServiceException.
	 * @return ControllerException
	 */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServiceException.class)
	ControllerException exceptionHandler(ServiceException e) {
		return new ControllerException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}
	
	/**
	 * Handle StorageException.
	 * @param e a StorageException.
	 * @return ControllerException
	 */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(StorageException.class)
	ControllerException exceptionHandler(StorageException e) {
		return new ControllerException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
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
    	return new ControllerException(HttpStatus.BAD_REQUEST.value(), sb.toString());
    }
}
