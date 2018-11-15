package com.game.kalah.repository;

import com.game.kalah.domain.Game;
import static com.game.kalah.utils.Constants.*;
import static com.game.kalah.utils.Status.*;
import java.util.Arrays;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Nesrin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class GameRepositoryTest {

         private Integer[] INITIAL_BOARD;
         private Game testGame;

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
                  assertThat(repository.findAll()).containsExactly(newGame);

                  // 3- apply change on the saved game then save again to chek update effect
                  
                  
         }

}
