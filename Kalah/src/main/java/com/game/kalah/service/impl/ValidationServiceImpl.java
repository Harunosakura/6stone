/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.kalah.service.impl;

import com.game.kalah.exception.WrongPitException;
import com.game.kalah.service.ValidationService;
import com.game.kalah.utils.Constants;
import com.game.kalah.utils.Status;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nesrin
 */
@Service
public class ValidationServiceImpl implements ValidationService {

         @Override
         public void validatePit(Integer pit) throws WrongPitException {
                  if (isKalah(pit))
                           throw new WrongPitException(Constants.ERR_KALAH_PIT);
                  if (pit < 1 || pit > 13)
                           throw new WrongPitException(Constants.ERR_WRONG_PIT);
         }

         /**
          *
          * Determines if the move is a valid one.
          *
          * @param gameStatus represents the current player turn based on class
          * {@link Status}
          * @param pit
          * @return true if the selected pit is valid for player, and false if
          * pit if the other player
          */
         @Override
         public boolean validateAction(Status gameStatus, Integer pit) throws WrongPitException {
                  if ((gameStatus.equals(Status.PLAYER1TURN) && 0 <= pit && pit <= 5)
                          || (gameStatus.equals(Status.PLAYER2TURN) && 7 <= pit && pit <= 12))
                           return true;

                  throw new WrongPitException(Constants.ERR_WRONG_PIT);
         }

         /**
          * Check if the pit is Kalah or not Kalah is at pit number 6 ad 13.
          *
          * @param pit
          * @return true if pit is a player home (Kalah) and false if others
          */
         @Override
         public boolean isKalah(Integer pit) {
                  return pit == Constants.KALAH_1 || pit == Constants.KALAH_2;
         }

         /**
          * Check the player requested pit is his own or not
          *
          * @param player
          * @param pit
          * @return true if player 1 requested from (0->6) and player 2 from
          * (7->12)
          */
         @Override
         public boolean isValidPlayerPit(Integer player, Integer pit) {
                  return (player == 1 && (0 <= pit && pit <= 5))
                          || (player == 2 && (7 <= pit && pit <= 12));
         }

}
