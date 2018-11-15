package com.game.kalah.service.impl;

import com.game.kalah.domain.Game;
import com.game.kalah.dto.GameDTO;
import com.game.kalah.repository.GameRepository;
import com.game.kalah.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.game.kalah.service.GameInProgressService;
import com.game.kalah.service.GameStatusService;
import com.game.kalah.service.ResponseService;
import com.game.kalah.service.ValidationService;
import java.util.List;
import static com.game.kalah.utils.Constants.*;
import static com.game.kalah.utils.Status.*;

/**
 * Class GameServiceImpl is responsible for handling the following actions:<br>
 * <ul>
 * <li>Moving pits according to players selection</li>
 * </ul>
 *
 * @author Nesrin
 */
@Service
public class GameInProgressServiceImpl implements GameInProgressService {

         @Autowired
         private GameRepository repository;

         @Autowired
         private ResponseService responseService;

         @Autowired
         private GameStatusService gameStatusService;

         @Autowired
         private ValidationService validationService;

         /**
          * Execute the game as per the player move and update the board
          * position.
          *
          * @param gameId
          * @param pit
          * @return {@link ResponseDTO} with the effect of required move set
          */
         @Override
         public GameDTO play(Integer gameId, Integer pit) {
                  // Work with real Index
                  pit--;

                  // Validate parameters
                  validationService.validatePit(pit);

                  // Load Game details
                  Game g = gameStatusService.getGameById(gameId);

                  // Validate action parameters
                  validationService.validateAction(g.getStatus(), pit);

                  // Apply action
                  g = applyStepMove(g, pit);

                  // Check game status after move
                  g = checkGameStatus(g);

                  // Save game
                  repository.save(g);

                  //return DTO
                  return responseService.prepareResponseObject(g, false);

         }

         /**
          * Determines if game has concluded and winner.
          */
         private Game checkGameStatus(Game g) {

                  Integer pitSumSideOne = 0;
                  List<Integer> board = g.getBoardList();

                  for (Integer i = 0; i < KALAH_1; i++)
                           pitSumSideOne += board.get(i);

                  Integer pitSumSideTwo = 0;

                  for (Integer i = 7; i < KALAH_2; i++)
                           pitSumSideTwo += board.get(i);

                  if (pitSumSideOne == 0 || pitSumSideTwo == 0) {

                           board.set(KALAH_1,
                                   board.get(KALAH_1) + pitSumSideOne);

                           board.set(KALAH_2,
                                   board.get(KALAH_2) + pitSumSideTwo);

                           if (board.get(KALAH_1) > board.get(KALAH_2))
                                    g.setStatus(Status.PLAYER1WINS);
                           else
                                    g.setStatus(Status.PLAYER2WINS);

                           for (int i = 0; i < MAX_NUMBER_OF_PITS; i++) {
                                    if (validationService.isKalah(i))
                                             continue;
                                    board.set(i, 0);
                           }
                           g.setMessage(END_MESSAGE);
                  }
                  g.setBoardList(board);

                  LOGGER.info("End  checkGameStatus {}", g);

                  return g;
         }

         /**
          * Get the next pit value
          *
          * @param pit
          * @return the next pit value
          */
         private Integer next(Integer pit) {
                  pit++;
                  return pit % MAX_NUMBER_OF_PITS;
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

         /**
          * Determines and update player turn.
          *
          * @param {@link Status}
          * @return {@link Status} with updated turn status
          */
         private Status setNextTurn(Status currentStatus) {

                  if (currentStatus.equals(Status.PLAYER1TURN))
                           return PLAYER2TURN;

                  return PLAYER1TURN;
         }

         private Game applyStepMove(Game g, Integer pit) {
                  LOGGER.info("Start action play {} {}", g, pit);

                  List<Integer> board = g.getBoardList();

                  //set the player value to use in next steps
                  int player = 2;
                  if (g.getStatus().equals(Status.PLAYER1TURN))
                           player = 1;

                  // find the index of current player Kalah
                  int kalah = getKalah(player);

                  Integer lastDropPit = pit;

                  // if pits greater than zero then 
                  //start adding them to the next pits  except the other player Kalah
                  int currentPitPieces = board.get(pit);
                  validationService.validateAvailablePieces(currentPitPieces);

                  while (currentPitPieces > 0) {
                           Integer dropPit = next(lastDropPit);
                           // if the other player Kalah then , bypass adding a piece
                           if (validationService.isKalah(dropPit) && kalah != dropPit) {
                                    lastDropPit = dropPit;
                                    continue;
                           }
                           board.set(
                                   dropPit,
                                   board.get(dropPit) + 1);
                           currentPitPieces--;
                           lastDropPit = dropPit;
                  }
                  // if the last piece was added in pit that contains zero pieces,
                  // the take all the opposite pit content into the current player Kalah
                  // in addition to his last piece
                  if (validationService.isValidPlayerPit(player, lastDropPit)
                          && board.get(lastDropPit) == 1) {

                           int oppositePitPiecesCount = board.get(kalah)
                                   + board.get(12 - lastDropPit)
                                   + board.get(lastDropPit);

                           board.set(kalah, oppositePitPiecesCount);
                           board.set(lastDropPit, 0);
                           board.set(12 - lastDropPit, 0);
                  }
                  // set the pit selected by player to Zero
                  board.set(pit, 0);
                  g.setBoardList(board);
                  g.setMessage(IN_PROGRESS_MESSAGE);

                  // if the last piece was added to the Kalah pit 
                  // then the player is allowed to play again 
                  if (kalah != lastDropPit)
                           g.setStatus(setNextTurn(g.getStatus()));

                  LOGGER.info("End  game play {} {}", g, pit);

                  return g;
         }
}
