package com.game.kalah.exception;

/**
 *
 * @author Nesrin
 */
public class GameNotFoundException extends RuntimeException{

         public GameNotFoundException(String msg, long id) {
                  super(msg+id);
         }


}
