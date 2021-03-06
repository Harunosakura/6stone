package com.game.kalah.service.impl;

import com.game.kalah.domain.Game;
import com.game.kalah.dto.GameDTO;
import com.game.kalah.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.game.kalah.service.GameInProgressService;
import com.game.kalah.service.GameStatusService;
import com.game.kalah.service.ResponseService;
import java.util.List;
import static com.game.kalah.utils.Constants.*;
import static com.game.kalah.utils.MoveUtils.*;
import static com.game.kalah.utils.Status.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Class GameServiceImpl is responsible for handling the following actions:<br>
 * <ul>
 * <li>Moving pits according to players selection</li>
 * <li>Check status of game at end of current move</li>
 * </ul>
 *
 * @author Nesrin
 */
@Service
/**
 * The difference is increase of performance, in log4j the string is
 * concatenated every time the line is evaluated even if log level is lower than
 * debug so the string will never be used. <br>
 * slf4j, the string and parameters are passed through to the logger which only
 * substitutes them if the log message is actually to be used.
 */
@Slf4j
public class GameInProgressServiceImpl implements GameInProgressService {

         @Autowired
         private GameRepository repository;

         @Autowired
         private ResponseService responseService;

         @Autowired
         private GameStatusService gameStatusService;

         /**
          * Execute the game as per the player move and update the board
          * position.
          *
          * @param gameId
          * @param pit
          * @return {@link ResponseDTO} with the effect of required move set
          */
         @Override
         public GameDTO play(Long gameId, Integer pit) {
                  // Work with real Index
                  pit--;

                  // Validate parameters
                  validatePit(pit);

                  // Load Game details
                  Game g = gameStatusService.getGameById(gameId);

                  // Validate action parameters
                  validateAction(g.getStatus(), pit);

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
                                    g.setStatus(PLAYER1WINS);
                           else
                                    g.setStatus(PLAYER2WINS);

                           for (int i = 0; i < MAX_NUMBER_OF_PITS; i++) {
                                    if (isKalah(i))
                                             continue;
                                    board.set(i, 0);
                           }
                           g.setMessage(END_MESSAGE);
                  }
                  g.setBoardList(board);

                  log.info("End  checkGameStatus {}", g);

                  return g;
         }

         private Game applyStepMove(Game g, Integer pit) {
                  log.info("Start action play {} {}", g, pit);

                  List<Integer> board = g.getBoardList();

                  //set the player value to use in next steps
                  int player = 2;
                  if (g.getStatus().equals(PLAYER1TURN))
                           player = 1;

                  // find the index of current player Kalah
                  int kalah = getKalah(player);

                  Integer lastDropPit = pit;

                  // if pits greater than zero then 
                  //start adding them to the next pits  except the other player Kalah
                  int currentPitPieces = board.get(pit);

                  validateAvailablePieces(currentPitPieces);

                  while (currentPitPieces > 0) {
                           Integer dropPit = next(lastDropPit);
                           // if the other player Kalah then , bypass adding a piece
                           if (isKalah(dropPit) && kalah != dropPit) {
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
                  if (isValidPlayerPit(player, lastDropPit)
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

                  log.info("End  game play {} {}", g, pit);

                  return g;
         }
}
