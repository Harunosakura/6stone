package com.game.kalah.controller;

import com.game.kalah.service.GameStatusService;
import com.game.kalah.domain.Game;
import com.game.kalah.dto.GameReportDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 * This class exposes RESTFul end-points which are used to by client to
 * communicate with service.
 *
 * @author Nesrin
 *
 */
@Controller
public class GameStatusController {

         @Autowired
         private GameStatusService gameStatusService;

         /**
          * Check the current status of specific game
          *
          * @param gameId the requested game to get its details
          * @return ResponseDTO containing requested game details object with
          * each pit populated with 6-Stones is returned, which player turn and
          * the current state.
          */
         @RequestMapping(value = "/games/{gameId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
         public ResponseEntity<GameReportDTO> gameStatus(@PathVariable("gameId") Integer gameId) {

                  return new ResponseEntity<>(
                          new GameReportDTO(
                                  gameStatusService.getGameById(gameId)), 
                          HttpStatus.OK);
         }

}
