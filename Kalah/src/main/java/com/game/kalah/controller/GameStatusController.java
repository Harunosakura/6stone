package com.game.kalah.controller;

import com.game.kalah.domain.Game;
import com.game.kalah.service.GameStatusService;
import com.game.kalah.dto.GameDTO;
import com.game.kalah.repository.GameRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class exposes RESTFul end-points which are used to by client to
 * communicate with service.
 *
 * @author Nesrin
 *
 */
@RestController
public class GameStatusController {

         @Autowired
         private GameStatusService gameService;
         @Autowired
         private GameRepository repository;

         /**
          * This service end-point initialize and starts a new GameServiceImpl.
          *
          * @return ResponseDTO containing new game details object with each pit
          * populated with 6-Stones is returned.
          *
          */
         @PostMapping(value = "/games", produces = MediaType.APPLICATION_JSON_VALUE)
         public ResponseEntity<GameDTO> startNewGame() {

                  GameDTO newGame = gameService.createNewGame();
                  return new ResponseEntity<>(newGame, HttpStatus.CREATED);
         }

         /**
          * Check the current status of specific game
          *
          * @param gameId the requested game to get its details
          * @return ResponseDTO containing requested game details object with
          * each pit populated with 6-Stones is returned, which player turn and
          * the current state.
          */
         @GetMapping(value = "/games/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
         public ResponseEntity<GameDTO> gameStatus(@PathVariable("gameId") Long gameId) {

                  return new ResponseEntity<>(
                          new GameDTO(
                                  gameService.getGameById(gameId)),
                          HttpStatus.OK);
         }

         @GetMapping(value = "/games/all")
         public List<GameDTO> allGames() {
                  List<GameDTO> games = new ArrayList<>();
                  Iterable<Game> g = repository.findAll();
                  g.forEach((_item) -> {
                           games.add(new GameDTO(_item));
                  });
                  return games;
//                  return new ResponseEntity<>(
//                          gameService.getAllGames(),
//                          HttpStatus.OK);
         }

}
