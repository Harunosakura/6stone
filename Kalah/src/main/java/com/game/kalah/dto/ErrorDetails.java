/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.kalah.dto;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Nesrin
 */
public class ErrorDetails {

         private String message;
         private LocalDateTime time;
         private HttpStatus status;
         private String details;

         public ErrorDetails(String message, HttpStatus status, String details) {
                  this.message = message;
                  this.time = LocalDateTime.now();
                  this.status = status;
                  this.details = details;
         }

         /**
          * @return the message
          */
         public String getMessage() {
                  return message;
         }

         /**
          * @param message the message to set
          */
         public void setMessage(String message) {
                  this.message = message;
         }

         /**
          * @return the time
          */
         public LocalDateTime getTime() {
                  return time;
         }

         /**
          * @param time the time to set
          */
         public void setTime(LocalDateTime time) {
                  this.time = time;
         }

         /**
          * @return the status
          */
         public HttpStatus getStatus() {
                  return status;
         }

         /**
          * @param status the status to set
          */
         public void setStatus(HttpStatus status) {
                  this.status = status;
         }

         /**
          * @return the details
          */
         public String getDetails() {
                  return details;
         }

         /**
          * @param details the details to set
          */
         public void setDetails(String details) {
                  this.details = details;
         }

}
