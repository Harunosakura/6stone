package com.game.kalah.repository;

import com.game.kalah.domain.Game;
import static com.game.kalah.utils.Constants.*;
import static com.game.kalah.utils.Status.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
//import static org.assertj.core.api.Assertions.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

/**
 *
 * @author Nesrin
 */
@RunWith(SpringRunner.class)
// DataJpaTest supports rollback after running every test case
@DataJpaTest
@ActiveProfiles("test")

public class GameRepositoryTest {

         private Integer[] INITIAL_BOARD;
         private Game testGame;
         private static Game palyedGame;

         @Autowired
         TestEntityManager em;

         @Autowired
         private GameRepository repository;

         @Before
         public void setup() {
                  INITIAL_BOARD = new Integer[]{6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0};
                  testGame = new Game();
                  testGame.setBoardList(Arrays.asList(INITIAL_BOARD));
                  testGame.setStatus(PLAYER1TURN);
                  //  testGame.setId(anyInt());
                  testGame.setMessage(START_MESSAGE);
                  testGame.setId(1);
                  fisrtPitPlayerOneMove();
         }

         @Test
         public void test_save_game() {
                  // 1- Insert new game
                  Game newGame = repository.save(
                          new Game(START_MESSAGE)
                  );
                  // 2- Check saved game data for reporting
                  Optional<Game> retrieved = repository.findById(1);
                  Game savedGame = retrieved.get();

                  // playing with test options :)
                  /*
                  * return to a good reference 
                  * https://objectpartners.com/2013/09/18/the-benefits-of-using-assertthat-over-other-assert-methods-in-unit-tests/
                   */
                  //import static org.junit.Assert.*;
                  assertNotNull(newGame);
                  assertThat(newGame, isA(Game.class));
                  assertThat(savedGame, instanceOf(Game.class));
                  assertThat(testGame, is(newGame));
                  assertThat(testGame, equalTo(savedGame));
                  assertEquals(testGame, savedGame);
                  assertNotSame("Not Same", retrieved, testGame);
                  assertThat(testGame, sameInstance(testGame));
                  assertTrue(savedGame.equals(testGame));

                  //import static  org.assertj.core.api.Assertions.*;
//                  assertThat(repository.findAll()).containsExactly(newGame);
                  savedGame.setBoardList(
                          new ArrayList<>(Arrays.asList(new Integer[]{0, 7, 7, 7, 7, 7, 1, 6, 6, 6, 6, 6, 6, 0})));
                  // 3- apply change on the saved game then save again to chek update effect
                  Game afterFirstMove = repository.save(savedGame);
                  assertNotNull(afterFirstMove);
                  assertThat(afterFirstMove, isA(Game.class));
                  assertEquals(savedGame, afterFirstMove);

         }

         @Test
         public void should_find_no_games_if_repository_is_empty() {
                  Iterable<Game> games = repository.findAll();

                  assertTrue(games.spliterator().estimateSize() == 0);
         }

         private static void fisrtPitPlayerOneMove() {
                  palyedGame = new Game();
                  palyedGame.setId(1);
                  palyedGame.setMessage(IN_PROGRESS_MESSAGE + palyedGame.getId());
                  palyedGame.setStatus(PLAYER1TURN);
                  palyedGame.setBoardList(Arrays.asList(new Integer[]{0, 7, 7, 7, 7, 7, 1, 6, 6, 6, 6, 6, 6, 0}));

         }
}
