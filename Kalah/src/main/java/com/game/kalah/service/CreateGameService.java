package com.game.kalah.service;

import com.game.kalah.dto.GameDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Nesrin
 */
public interface CreateGameService {

         Logger LOGGER = LoggerFactory.getLogger(CreateGameService.class);
         Integer[] INITIAL_BOARD = new Integer[]{6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0};

         GameDTO createNewGame();

}
