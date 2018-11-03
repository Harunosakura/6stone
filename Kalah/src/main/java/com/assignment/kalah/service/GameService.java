package com.assignment.kalah.service;

import com.assignment.kalah.domain.Game;
import com.assignment.kalah.exception.GameNotFoundException;
import com.assignment.kalah.exception.WrongPitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Nesrin
 */
public interface GameService {

         static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

         public Game createNewGame();

         public boolean isValidMove(Game game, Integer pit);

         public Game getGameById(Integer gId) throws GameNotFoundException;

         public void saveGames(Game game);

         public Game play(Game game, Integer pit) throws WrongPitException;
}
