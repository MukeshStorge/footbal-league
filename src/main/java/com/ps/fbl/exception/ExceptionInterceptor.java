package com.ps.fbl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

  @ExceptionHandler(FBLException.class)
  public final ResponseEntity<Object> handleAllExceptions(FBLException ex) {
	  FblExceptionSchema exceptionResponse =
        new FblExceptionSchema(
            ex.getMessage(), ex.getCode());
    return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}