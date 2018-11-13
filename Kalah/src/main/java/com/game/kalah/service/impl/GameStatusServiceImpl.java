package com.game.kalah.service.impl;

import com.game.kalah.domain.Game;
import com.game.kalah.exception.GameNotFoundException;
import com.game.kalah.repository.GameRepository;
import com.game.kalah.service.GameStatusService;
import com.game.kalah.utils.Constants;
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
                           throw new GameNotFoundException(Constants.ERR_GAME_NOT_FOUND, id);
         }

}
