package com.game.kalah.service.impl;

import com.game.kalah.domain.Game;
import com.game.kalah.dto.GameDTO;
import com.game.kalah.exception.GameNotFoundException;
import com.game.kalah.repository.GameRepository;
import com.game.kalah.service.GameStatusService;
import com.game.kalah.service.ResponseService;
import static com.game.kalah.utils.CollectionUtils.*;
import static com.game.kalah.utils.Constants.*;
import java.util.Map;
import java.util.Optional;
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
public class GameStatusServiceImpl implements GameStatusService {

         @Autowired
         private GameRepository repository;

         @Autowired
         private ResponseService responseService;

         /**
          * Retrieve {@link Game} with requested ID
          *
          * @param id selected by user
          * @return {@link Game} object with whole status
          * @throws GameNotFoundException in there is no game saved with that ID
          */
         @Override
         public Game getGameById(Integer id) throws GameNotFoundException {
                  Optional<Game> gameOp = repository.findById(id);
                  if (gameOp.isPresent())
                           return gameOp.get();
                  else
                           throw new GameNotFoundException(ERR_GAME_NOT_FOUND, id);
         }

         @Override
         public GameDTO createNewGame() {
                  return responseService.prepareResponseObject(
                          repository.save(
                                  new Game(START_MESSAGE)),
                          true);
         }

         @Override
         public Map<String, Object> getAllGames() {
                  Iterable<Game> games = repository.findAll();
                  return responseService.prepareResponseObjectForAll(copyIterator(games));
         }

}
