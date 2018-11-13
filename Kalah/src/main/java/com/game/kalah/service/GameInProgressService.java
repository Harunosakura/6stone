package com.game.kalah.service;

import com.game.kalah.exception.WrongPitException;
import com.game.kalah.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Nesrin
 */
public interface GameInProgressService {

         static final Logger LOGGER = LoggerFactory.getLogger(GameInProgressService.class);

         public ResponseDTO play(Integer gameId, Integer pit) throws WrongPitException;
}
