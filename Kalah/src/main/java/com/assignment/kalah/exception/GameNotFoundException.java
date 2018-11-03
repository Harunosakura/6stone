package com.assignment.kalah.exception;

/**
 *
 * @author Nesrin
 */
public class GameNotFoundException extends RuntimeException{

         public GameNotFoundException(String msg, int id) {
                  super(msg+id);
         }


}
