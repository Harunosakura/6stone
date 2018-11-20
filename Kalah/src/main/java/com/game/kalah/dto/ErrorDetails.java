/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.kalah.dto;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Nesrin
 */
@Data
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
}
