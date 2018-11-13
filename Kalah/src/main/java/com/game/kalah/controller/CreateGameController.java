package com.game.kalah.controller;

import com.game.kalah.dto.ResponseDTO;
import com.game.kalah.service.CreateGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class CreateGameController {

         @Autowired
         private CreateGameService creatGgameService;

         /**
          * This service end-point initialize and starts a new GameServiceImpl.
          *
          * @return ResponseDTO containing new game details object with each pit
          * populated with 6-Stones is returned.
          *
          */
         @RequestMapping(value = "/games", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
         public ResponseEntity<ResponseDTO> startNewKalahGame() {

                  ResponseDTO newGame = creatGgameService.createNewGame();
                  return new ResponseEntity<>(newGame, HttpStatus.CREATED);
         }

}
