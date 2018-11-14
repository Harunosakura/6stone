package com.game.kalah.service;

import com.game.kalah.dto.GameDTO;
import com.game.kalah.exception.WrongPitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Nesrin
 */
public interface GameInProgressService {

         static final Logger LOGGER = LoggerFactory.getLogger(GameInProgressService.class);

         public GameDTO play(Integer gameId, Integer pit) throws WrongPitException;
}
