package com.game.kalah.service;

import com.game.kalah.exception.WrongPitException;
import com.game.kalah.utils.Status;

/**
 *
 * @author Nesrin
 */
public interface ValidationService {

         public void validatePit(Integer pit) throws WrongPitException;

         public void validateAction(Status gameStatus, Integer pit) throws WrongPitException;

         public void validateAvailablePieces(int piecesCount);

         public boolean isKalah(Integer pit);

         public boolean isValidPlayerPit(Integer player, Integer pit);
}
