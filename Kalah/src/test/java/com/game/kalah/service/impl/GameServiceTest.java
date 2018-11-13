package com.game.kalah.service.impl;

import com.game.kalah.GameTest;
import com.game.kalah.domain.Game;
import com.game.kalah.exception.GameNotFoundException;
import com.game.kalah.exception.WrongPitException;
import com.game.kalah.repository.GameRepository;
import com.game.kalah.utils.Constants;
import com.game.kalah.utils.Status;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.game.kalah.service.GameInProgressService;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc

public class GameServiceTest extends GameTest {
//
//         @MockBean
//         private GameRepository repository;
//
//         @Autowired
//         private MockMvc mockMvc;
//
//         @Autowired
//         private GameInProgressService gameService;
//
//         @Test
//         public void testStartFirstGame() {
//                  given(repository.save(any())).willReturn(game);
//                  Game firstGame = gameService.createNewGame();
//                  assertEquals(game.toString(), firstGame.toString());
//                  verify(repository, times(1)).save(any());
//         }
//
//         @Test
//         public void testGameIFAvailable_ShouldNotThrowException() {
//                  Optional<Game> gameOp = Optional.of(game);
//                  given(repository.findById(any())).willReturn(gameOp);
//                  Game g = gameService.getGameById(1);
//                  assertEquals(game.toString(), g.toString());
//                  verify(repository, times(1)).findById(any());
//         }
//
//         @Test
//         public void testGameIFAvailable_ShouldThrowException() {
//                  Optional<Game> any = Optional.ofNullable(null);
//                  given(repository.findById(any())).willReturn(any);
//                  assertThatExceptionOfType(GameNotFoundException.class)
//                          .isThrownBy(() -> gameService.getGameById(0))
//                          .withMessage(Constants.ERR_GAME_NOT_FOUND + 0);
//                  verify(repository, times(1)).findById(any());
//
//         }
//
//         @Test
//         public void testPlayFirstMovePlayerOnePit1() {
//                  Integer[] testBoard = new Integer[]{0, 7, 7, 7, 7, 7, 1, 6, 6, 6, 6, 6, 6, 0};
//                  given(repository.save(any())).willReturn(any());
//                  Game gameAfterFirstPit = gameService.play(game, 0);
//                  List<Integer> actualBoard = gameAfterFirstPit.getBoardList();
//                  assertEquals(Arrays.asList(testBoard), actualBoard);
//                  assertEquals(Status.PLAYER1TURN, gameAfterFirstPit.getStatus());
//                  verify(repository, times(1)).save(any());
//         }
//
//         @Test
//         public void testPlaySecondMovePlayerOnePit1_ShouldThrowException() {
//                  Integer[] testBoard = new Integer[]{0, 7, 7, 7, 7, 7, 1, 6, 6, 6, 6, 6, 6, 0};
//                  given(repository.save(any())).willReturn(null);
//                  game.setBoardList(Arrays.asList(testBoard));
//                  assertThatExceptionOfType(WrongPitException.class)
//                          .isThrownBy(() -> gameService.play(game, 0))
//                          .withMessage(Constants.ERR_EMPTY_PIT);
//                  verify(repository, times(0)).save(any());
//         }
//
//         @Test
//         public void testPlayPlayerOneKalah_ShouldThrowException() {
//                  given(repository.save(any())).willReturn(null);
//                  assertThatExceptionOfType(WrongPitException.class)
//                          .isThrownBy(() -> gameService.play(game, 6))
//                          .withMessage(Constants.ERR_KALAH_PIT);
//                  verify(repository, times(0)).save(any());
//         }
//
//         @Test
//         public void testPlayPlayerOneOpponentKalah_ShouldThrowException() {
//                  given(repository.save(any())).willReturn(null);
//                  assertThatExceptionOfType(WrongPitException.class)
//                          .isThrownBy(() -> gameService.play(game, 13))
//                          .withMessage(Constants.ERR_KALAH_PIT);
//                  verify(repository, times(0)).save(any());
//         }
//
//         @Test
//         public void testPlayPlayerTwoKalah_ShouldThrowException() {
//                  given(repository.save(any())).willReturn(null);
//                  game.setStatus(Status.PLAYER2TURN);
//                  assertThatExceptionOfType(WrongPitException.class)
//                          .isThrownBy(() -> gameService.play(game, 13))
//                          .withMessage(Constants.ERR_KALAH_PIT);
//                  verify(repository, times(0)).save(any());
//         }
//
//         @Test
//         public void testPlayPlayerTwoOpponentKalah_ShouldThrowException() {
//                  given(repository.save(any())).willReturn(null);
//                  game.setStatus(Status.PLAYER2TURN);
//                  assertThatExceptionOfType(WrongPitException.class)
//                          .isThrownBy(() -> gameService.play(game, 6))
//                          .withMessage(Constants.ERR_KALAH_PIT);
//                  verify(repository, times(0)).save(any());
//
//         }
//
//         @Test
//         public void testPlayNegativeValuePit_ShouldThrowException() {
//                  given(repository.save(any())).willReturn(null);
//                  assertThatExceptionOfType(WrongPitException.class)
//                          .isThrownBy(() -> gameService.play(game, -1))
//                          .withMessage(Constants.ERR_INDEX_PIT);
//                  verify(repository, times(0)).save(any());
//
//         }
//
//         @Test
//         public void testPlayOneSecondPlayerPits() {
//                  assertFalse(gameService.isValidMove(game, 7));
//         }
//
//         @Test
//         public void testPlayTwoFirstPlayerPits() {
//                  game.setStatus(Status.PLAYER2TURN);
//                  assertFalse(gameService.isValidMove(game, 3));
//         }
//
//         @Test
//         public void testPlay2TurnIfLastPieceOfPlayer1NotInKalah() {
//                  given(repository.save(any())).willReturn(any());
//                  Game player2Turn = gameService.play(gameService.play(game, 0), 1);
//                  assertEquals(Status.PLAYER2TURN, player2Turn.getStatus());
//                  verify(repository, times(2)).save(any());
//
//         }
//
//         @Test
//         public void testPlay1TurnIfLastPieceOfPlayer2NotInKalah() {
//                  given(repository.save(any())).willReturn(any());
//                  Game player1TurnIfLast = gameService.play(gameService.play(gameService.play(game, 0), 1), 11);
//                  assertEquals(Status.PLAYER1TURN, player1TurnIfLast.getStatus());
//                  verify(repository, times(3)).save(any());
//         }
//
//         @Test
//         public void testPlay1EmptyLastPitOppositePlayer2NonEmptyPit() {
//                  given(repository.save(any())).willReturn(any());
//                  Integer[] testBoard = new Integer[]{0, 0, 1, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0};
//                  Integer[] expectdBoard = new Integer[]{0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0};
//                  game.setBoardList(Arrays.asList(testBoard));
//                  game.setId(0);
//                  Game player1TurnEmptyLast = gameService.play(game, 2);
//                  assertEquals(Arrays.asList(expectdBoard), player1TurnEmptyLast.getBoardList());
//                  verify(repository, times(1)).save(any());
//         }
//
//         @Test
//         public void testPlay2EmptyLastPitOppositePlayer1NonEmptyPit() {
//                  given(repository.save(any())).willReturn(any());
//                  Integer[] testBoard = new Integer[]{0, 0, 10, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0};
//                  Integer[] expectdBoard = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11};
//                  game.setStatus(Status.PLAYER2TURN);
//                  game.setBoardList(Arrays.asList(testBoard));
//                  Game player2TurnEmpty = gameService.play(game, 9);
//                  assertEquals(Arrays.asList(expectdBoard), player2TurnEmpty.getBoardList());
//                  verify(repository, times(1)).save(any());
//         }
//
//         @Test
//         public void testPlay1LastPiece() {
//                  given(repository.save(any())).willReturn(any());
//
//                  Integer[] testBoard = new Integer[]{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0};
//                  Integer[] expectdBoard = new Integer[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0};
//                  game.setBoardList(Arrays.asList(testBoard));
//                  game.setId(3);
//                  Game player1Turn = gameService.play(game, 5);
//                  assertEquals(Status.PLAYER1WINS, player1Turn.getStatus());
//                  assertEquals(Arrays.asList(expectdBoard), player1Turn.getBoardList());
//                  verify(repository, times(1)).save(any());
//
//         }
//
//         @Test
//         public void testPlay2LastPiece() {
//                  given(repository.save(any())).willReturn(any());
//                  Integer[] testBoard = new Integer[]{0, 0, 1, 0, 0, 2, 30, 0, 0, 0, 0, 0, 1, 38};
//                  Integer[] expectdBoard = new Integer[]{0, 0, 0, 0, 0, 0, 33, 0, 0, 0, 0, 0, 0, 39};
//                  game.setBoardList(Arrays.asList(testBoard));
//                  game.setId(2);
//                  game.setStatus(Status.PLAYER2TURN);
//                  Game player2TurnLastPiece = gameService.play(game, 12);
//                  assertEquals(Status.PLAYER2WINS, player2TurnLastPiece.getStatus());
//                  assertEquals(Arrays.asList(expectdBoard), player2TurnLastPiece.getBoardList());
//                  verify(repository, times(1)).save(any());
//
//         }
}
