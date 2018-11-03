package com.assignment.kalah.Controller;

import com.assignment.kalah.GameTest;
import com.assignment.kalah.exception.GameNotFoundException;
import com.assignment.kalah.service.GameService;
import com.assignment.kalah.utils.Constants;
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


/**
 *
 * @author Nesrin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest extends GameTest {

         @MockBean
         private GameService gameService;

         @Autowired
         private MockMvc mockMvc;

         @Test
         public void testCreateNewGame() throws Exception {
                  given(gameService.createNewGame()).willReturn(game);

                  mockMvc.perform(get("/games")).andDo(print())
                          .andExpect(status().isOk())
                          .andExpect(jsonPath("$.id").value(1))
                          .andExpect(jsonPath("$.message").value("New game started with ID: 1"))
                          .andExpect(jsonPath("$.uri").value(url + "/" + 1));
                  verify(gameService, times(1)).createNewGame();
         }

         @Test
         public void testGameStatus_return_game() throws Exception {
                  given(gameService.getGameById(1)).willReturn(game);
                  mockMvc.perform(get("/games/1")).andDo(print())
                          .andExpect(status().isOk())
                          .andExpect(jsonPath("$.id").value(1))
                          .andExpect(jsonPath("$.status").value("PLAYER1TURN"))
                          .andExpect(jsonPath("$.boardList").isArray())
                          .andExpect(jsonPath("$.message").isString());

                  verify(gameService, times(1)).getGameById(1);
         }

         @Test
         public void testGameStatus_return_GameNotFoundException() throws Exception {
                  given(gameService.getGameById(anyInt()))
                          .willThrow(new GameNotFoundException(Constants.ERR_GAME_NOT_FOUND, 2));
                  mockMvc.perform(get("/games/2")).andDo(print())
                          .andExpect(status().isNotFound())
                          .andExpect(content().string("NO Game started with ID: 2"));
                  verify(gameService, times(1)).getGameById(2);
         }

         @Test
         public void testGameStatus_return_NumberFormatException() throws Exception {
                  given(gameService.getGameById(any()))
                          .willThrow(NumberFormatException.class);
                  mockMvc.perform(get("/games/f")).andDo(print())
                          .andExpect(status().isNotAcceptable());

         }

         @Test
         public void testPlay_big_pit_WrongPitException() throws Exception {
                  mockMvc.perform(get("/games/1/pit/15")).andDo(print())
                          .andExpect(status().isExpectationFailed());
         }

         @Test
         public void testPlay_small_pit_WrongPitException() throws Exception {
                  mockMvc.perform(get("/games/1/pit/0")).andDo(print())
                          .andExpect(status().isExpectationFailed());
         }

         @Test
         public void testPly_return_GameNotFoundException() throws Exception {
                  given(gameService.getGameById(anyInt()))
                          .willThrow(new GameNotFoundException(Constants.ERR_GAME_NOT_FOUND, 2));
                  mockMvc.perform(get("/games/2/pit/1")).andDo(print())
                          .andExpect(status().isNotFound())
                          .andExpect(content().string("NO Game started with ID: 2"));
                  verify(gameService, times(1)).getGameById(2);
         }

         @Test
         public void testPly_return_Game() throws Exception {
                  given(gameService.getGameById(anyInt())).willReturn(game);
                  given(gameService.isValidMove(any(), any()))
                          .willReturn(true);
                  given(gameService.play(any(), any()))
                          .willReturn(game);
                  mockMvc.perform(get("/games/1/pit/1")).andDo(print())
                          .andExpect(status().isOk());
                  verify(gameService, times(1)).getGameById(anyInt());
                  verify(gameService, times(1)).isValidMove(any(), any());
                  verify(gameService, times(1)).play(any(), any());
         }

         @Test
         public void testPly_return_WrongPitException() throws Exception {
                  given(gameService.getGameById(anyInt())).willReturn(game);
                  given(gameService.isValidMove(any(), any()))
                          .willReturn(false);
                  given(gameService.play(any(), any()))
                          .willReturn(game);
                  mockMvc.perform(get("/games/1/pit/1")).andDo(print())
                          .andExpect(status().isExpectationFailed());
                  verify(gameService, times(1)).getGameById(anyInt());
                  verify(gameService, times(1)).isValidMove(any(), any());
                  verify(gameService, times(0)).play(any(), any());
         }

}
