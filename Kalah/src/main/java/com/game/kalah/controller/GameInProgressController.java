package com.game.kalah.controller;

import com.game.kalah.domain.Game;
import com.game.kalah.dto.ResponseDTO;
import com.game.kalah.exception.WrongPitException;
import com.game.kalah.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.game.kalah.service.ResponseService;
import com.game.kalah.service.GameInProgressService;

/**
 * This class exposes RESTFul end-points which are used to by client to
 * communicate with service.
 *
 * @author Nesrin
 *
 */
@RestController
@RequestMapping()
public class GameInProgressController {

         @Autowired
         private GameInProgressService gameService;

         /**
          * This service end-point makes a play of the game and change the game
          * state according the selected pit by a player.
          *
          * @param gameId
          * @param pitId
          * @return Updated Game object from the move made a player.
          */
         @RequestMapping(value = "/games/{gameId}/pit/{pitId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
         public ResponseDTO play(
                 @PathVariable("gameId") Integer gameId,
                 @PathVariable("pitId") Integer pitId) {
                  return gameService.play(gameId, pitId);

         }
}
