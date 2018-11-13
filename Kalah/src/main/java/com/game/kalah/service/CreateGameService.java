package com.game.kalah.service;

import com.game.kalah.domain.Game;
import com.game.kalah.exception.WrongPitException;
import com.game.kalah.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Nesrin
 */
public interface CreateGameService {

         static final Logger LOGGER = LoggerFactory.getLogger(CreateGameService.class);

         public ResponseDTO createNewGame();

}
