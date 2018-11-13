package com.game.kalah.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author Nesrin
 */
@ControllerAdvice
public class GlobalExceptionHandler {

         @ExceptionHandler(Throwable.class)
         public ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
                  HttpHeaders headers = new HttpHeaders();

                  if (ex instanceof GameNotFoundException)

                           return handleExceptionInternal(ex,
                                   new HttpHeaders(), HttpStatus.NOT_FOUND, request);
                  else if (ex instanceof WrongPitException)
                           return handleExceptionInternal(ex,
                                   new HttpHeaders(), HttpStatus.EXPECTATION_FAILED, request);

                  return handleExceptionInternal(ex, headers, HttpStatus.NOT_ACCEPTABLE, request);
         }

         protected ResponseEntity<Object> handleExceptionInternal(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
                  if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status))
                           request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);

                  return new ResponseEntity<>(ex.getMessage(), headers, status);
         }
}
