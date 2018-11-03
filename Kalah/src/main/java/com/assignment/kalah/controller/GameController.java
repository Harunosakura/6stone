package com.assignment.kalah.controller;

import com.assignment.kalah.domain.Game;
import com.assignment.kalah.utils.ResponseObject;
import com.assignment.kalah.exception.WrongPitException;
import com.assignment.kalah.service.GameService;
import com.assignment.kalah.service.ResponseObjectService;
import com.assignment.kalah.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class exposes RESTFul end-points which are used to by client to
 * communicate with service.
 *
 * @author Nesrin
 *
 */
@RestController
@RequestMapping()
public class GameController {

         @Autowired
         private GameService gameService;
         @Autowired
         private ResponseObjectService responseService;

         /**
          * This service end-point initialize and starts a new GameServiceImpl.
          *
          * @return ResponseObject containing new game details object with each
          * pit populated with 6-Stones is returned.
          *
          */
         @RequestMapping(value = "/games", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
         public ResponseObject startNewKalahGame() {
                  return responseService.prepareResponseObject(gameService.createNewGame(), true);
         }

         /**
          * Check the current status of specific game
          *
          * @param gameId the requested game to get its details
          * @return ResponseObject containing requested game details object with
          * each pit populated with 6-Stones is returned, which player turn and
          * the current state.
          */
         @RequestMapping(value = "/games/{gameId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
         public Game gameStatus(@PathVariable("gameId") Integer gameId) {
                  return gameService.getGameById(gameId);
         }

         /**
          * This service end-point makes a play of the game and change the game
          * state according the selected pit by a player.
          *
          * @param gameId
          * @param pitId
          * @return Updated Game object from the move made a player.
          */
         @RequestMapping(value = "/games/{gameId}/pit/{pitId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
         public ResponseObject play(
                 @PathVariable("gameId") Integer gameId,
                 @PathVariable("pitId") Integer pitId) {

                  if (pitId < 1 || pitId > Constants.NUMBER_OF_PITS)
                           throw new WrongPitException(Constants.ERR_INDEX_PIT);
                  pitId--;
                  Game game = gameService.getGameById(gameId);

                  if (gameService.isValidMove(game, pitId))
                           game = gameService.play(game, pitId);
                  else
                           throw new WrongPitException(Constants.ERR_WRONG_PIT);
                  gameService.saveGames(game);
                  return responseService.prepareResponseObject(game, false);
         }
}
