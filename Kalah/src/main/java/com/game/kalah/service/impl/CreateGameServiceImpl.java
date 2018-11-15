package com.game.kalah.service.impl;

import com.game.kalah.domain.Game;
import com.game.kalah.dto.GameDTO;
import com.game.kalah.repository.GameRepository;
import com.game.kalah.service.CreateGameService;
import com.game.kalah.service.ResponseService;
import com.game.kalah.utils.Status;
import com.game.kalah.utils.Constants;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class GameServiceImpl is responsible for handling the following actions:<br>
 * <ul>
 * <li>Checking the {@link Game} status</li>
 * </ul>
 *
 * @author Nesrin
 */
@Service
public class CreateGameServiceImpl implements CreateGameService {

         @Autowired
         private GameRepository repository;

         @Autowired
         private ResponseService responseService;

         /**
          * Initializes Kalah game with start board configuration and status.
          *
          * @return {@link Game} with new id not used previously
          */
         private Game insertGame() {
                  Game saveNewGame = repository.save(
                          new Game(Arrays.asList(INITIAL_BOARD.clone()),
                                  Status.PLAYER1TURN,
                                  Constants.START_MESSAGE
                          ));
                  LOGGER.info("Saved Game{}", saveNewGame);
                  return saveNewGame;
         }

         @Override
         public GameDTO createNewGame() {
                  return responseService.prepareResponseObject(insertGame(), true);
         }
}
