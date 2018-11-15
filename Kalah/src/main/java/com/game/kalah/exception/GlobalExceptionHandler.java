package com.game.kalah.exception;

import com.game.kalah.dto.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Nesrin
 */
@ControllerAdvice
public class GlobalExceptionHandler  {

         /**
          *
          * @param ex
          * @param request
          * @return
          */
         @ExceptionHandler(Throwable.class)
         public 
         ResponseEntity<Object> handleExceptionInternal(Exception ex, WebRequest request) {
                  if (ex instanceof GameNotFoundException)
                           return new ResponseEntity<>(
                                   new ErrorDetails(ex.getMessage(), HttpStatus.NOT_FOUND,
                                           request.getDescription(false)), HttpStatus.NOT_FOUND
                           );
                  else if (ex instanceof WrongPitException)
                           return new ResponseEntity<>(
                                   new ErrorDetails(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE,
                                           request.getDescription(false)), HttpStatus.NOT_ACCEPTABLE
                           );
                  else
                           return new ResponseEntity<>(
                                   new ErrorDetails(ex.getMessage(), HttpStatus.BAD_REQUEST,
                                           request.getDescription(false)),
                                   HttpStatus.BAD_REQUEST);
//                  if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status))
//                           request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
//
//                  return new ResponseEntity<>(ex.getMessage(), headers, status);
         }
}
