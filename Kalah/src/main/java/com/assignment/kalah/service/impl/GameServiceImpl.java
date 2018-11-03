package com.assignment.kalah.service.impl;

import com.assignment.kalah.domain.Game;
import com.assignment.kalah.exception.GameNotFoundException;
import com.assignment.kalah.exception.WrongPitException;
import com.assignment.kalah.repository.GameRepository;
import com.assignment.kalah.service.GameService;
import com.assignment.kalah.utils.Status;
import com.assignment.kalah.utils.Constants;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class GameServiceImpl is responsible for handling the following actions:<br>
 * <ul>
 * <li>Creating a new {@link Game} </li>
 * <li>Checking the {@link Game} status</li>
 * <li>Moving pits according to players selection</li>
 * </ul>
 *
 * @author Nesrin
 */
@Service
public class GameServiceImpl implements GameService {

         @Autowired
         private GameRepository repository;
         static Integer[] INITIAL_BOARD;
         private static final int KALAH_1 = 6;
         private static final int KALAH_2 = 13;

         /**
          * Initializes Kalah game with start board configuration and status.
          *
          * @return {@link Game} with new id not used previously
          */
         @Override
         public Game createNewGame() {
                  INITIAL_BOARD = new Integer[]{6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0};
                  Game saveNewGame = repository.save(
                          new Game(Arrays.asList(INITIAL_BOARD.clone()),
                                  Status.PLAYER1TURN,
                                  Constants.START_MESSAGE
                          ));
                  LOGGER.info("Saved Game{}", saveNewGame);
                  return saveNewGame;
         }

         /**
          * Determines and update player turn.
          *
          * @param {@link Game}
          * @return {@link Game} with updated turn status
          */
         private Game updateNextTurn(Game game) {

                  if (game.getStatus().equals(Status.PLAYER1TURN))
                           game.setStatus(Status.PLAYER2TURN);
                  else if (game.getStatus().equals(Status.PLAYER2TURN))
                           game.setStatus(Status.PLAYER1TURN);
                  return game;
         }

         /**
          *
          * Determines if the move is a valid one.
          *
          * @param game
          * @param pit
          * @return true if the selected pit is valid for player, and false if
          * pit if the other player
          */
         @Override
         public boolean isValidMove(Game game, Integer pit) {
                  if (game.getStatus().equals(Status.PLAYER1TURN))
                           return 0 <= pit && pit <= 5;
                  else if (game.getStatus().equals(Status.PLAYER2TURN))
                           return 7 <= pit && pit <= 12;
                  return false;
         }

         /**
          * Execute the game as per the player move and update the board
          * position.
          *
          * @param game
          * @param pit
          * @return {@link Game} with the effect of required move set
          */
         @Override
         public Game play(Game game, Integer pit) throws WrongPitException {
                  LOGGER.info("Start game play {} {}", game, pit);
                  if (pit < 0 || pit > KALAH_2)
                           throw new WrongPitException(Constants.ERR_INDEX_PIT);
                  if (isKalah(pit))
                           //handle exception; "Can't start from a player Kalah"
                           throw new WrongPitException(Constants.ERR_KALAH_PIT);
                  Integer pieces = collectPieces(game, pit);
                  if (pieces == 0)
                           //handle exception; "Zero pieces at select pit number"+(pit+1)
                           throw new WrongPitException(Constants.ERR_EMPTY_PIT);
                  else
                           game.getBoardList().set(pit, 0);
                  //set the player value to use in next steps
                  int player = 2;
                  if (game.getStatus().equals(Status.PLAYER1TURN))
                           player = 1;

                  Integer lastDropPit = pit;
                  // if pits greater than zero then 
                  //start adding them to the next pits  except the other player Kalah
                  while (pieces > 0) {
                           Integer dropPit = next(lastDropPit);
                           // if the other player Kalah then , bypass adding a piece
                           if (isKalah(dropPit) && getKalah(player) != dropPit) {
                                    lastDropPit = dropPit;
                                    continue;
                           }
                           game.getBoardList().set(
                                   dropPit,
                                   game.getBoardList().get(dropPit) + 1);
                           pieces--;
                           lastDropPit = dropPit;
                  }
                  // if the last piece was added in pit that contains zero pieces,
                  // the take all the opposite pit content into the current player Kalah
                  // in addition to his last piece
                  if (isMyPit(player, lastDropPit)
                          && game.getBoardList().get(lastDropPit) == 1) {
                           int x = game.getBoardList().get(getKalah(player))
                                   + game.getBoardList().get(12 - lastDropPit)
                                   + game.getBoardList().get(lastDropPit);
                           game.getBoardList().set(getKalah(player), x);
                           game.getBoardList().set(lastDropPit, 0);
                           game.getBoardList().set(12 - lastDropPit, 0);
                  }
                  // if the last piece was added to the Kalah pit 
                  // then the player is allowed to play again 
                  if (getKalah(player) != lastDropPit)
                           game = updateNextTurn(game);
                  game.setMessage(Constants.IN_PROGRESS_MESSAGE);
                  LOGGER.info("End  game play {} {}", game, pit);

                  return checkGameStatus(game);
         }

         /**
          * Determines if game has concluded and winner.
          */
         private Game checkGameStatus(Game game) {
                  Integer pitSumSideOne = 0;
                  for (Integer i = 0; i < KALAH_1; i++)
                           pitSumSideOne += game.getBoardList().get(i);
                  Integer pitSumSideTwo = 0;
                  for (Integer i = 7; i < KALAH_2; i++)
                           pitSumSideTwo += game.getBoardList().get(i);
                  if (pitSumSideOne == 0 || pitSumSideTwo == 0) {
                           game.getBoardList().set(KALAH_1,
                                   game.getBoardList().get(KALAH_1) + pitSumSideOne);
                           game.getBoardList().set(KALAH_2,
                                   game.getBoardList().get(KALAH_2) + pitSumSideTwo);
                           if (game.getBoardList().get(KALAH_1) > game.getBoardList().get(KALAH_2))
                                    game.setStatus(Status.PLAYER1WINS);
                           else
                                    game.setStatus(Status.PLAYER2WINS);
                           for (int i = 0; i < Constants.NUMBER_OF_PITS; i++) {
                                    if (i == KALAH_1 || i == KALAH_2) {
                                             System.out.println("i:" + i);
                                             continue;
                                    }
                                    game.getBoardList().set(i, 0);
                           }
                           game.setMessage(Constants.END_MESSAGE);
                  }
                  LOGGER.info("End  checkGameStatus {}", game);
                  saveGames(game);
                  return game;
         }

         /**
          * Check if the pit is Kalah or not Kalah is at pit number 6 ad 13.
          *
          * @param pit
          * @return true if pit is a player home (Kalah) and false if others
          */
         private boolean isKalah(Integer pit) {
                  return pit == KALAH_1 || pit == KALAH_2;
         }

         /**
          * Get the next pit value
          *
          * @param pit
          * @return the next pit value
          */
         private Integer next(Integer pit) {
                  pit++;
                  return pit % Constants.NUMBER_OF_PITS;
         }

         /**
          * Get the pieces count from the pit selected by the player to add them
          * to the next pits
          *
          * @param game a {@link Game}
          * @param pit the pit selected by the player
          * @return pieces count from the pit selected
          */
         private Integer collectPieces(Game game, Integer pit) {
                  Integer pieces = game.getBoardList().get(pit);
                  return pieces;
         }

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

         /**
          * Save the game object into database with its last state
          *
          * @param game
          */
         @Override
         public void saveGames(Game game) {
                  repository.save(game);
         }

         /**
          * Check the player requested pit is his own or not
          *
          * @param player
          * @param pit
          * @return true if player 1 requested from (0->6) and player 2 from
          * (7->12)
          */
         private boolean isMyPit(int player, int pit) {
                  return (player == 1) ? (0 <= pit && pit <= 5) : (7 <= pit && pit <= 12);
         }

         /**
          * Find the value of player Kalah
          *
          * @param player which is the current player turn
          * @return 6 if player 1, and 13 if player 2
          */
         private int getKalah(int player) {
                  return (player == 1) ? KALAH_1 : KALAH_2;
         }

}
