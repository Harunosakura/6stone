package com.game.kalah.exception;

import static com.game.kalah.utils.Constants.*;
import java.io.IOException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
/**
 *
 * @author Nesrin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GameNotFoundExceptionTest {

         @Rule
         public ExpectedException thrown = ExpectedException.none();

         @Test
         public void throwsGameNotFoundExceptionWithMessage() {
                  thrown.expect(GameNotFoundException.class);
                  thrown.expectMessage(ERR_GAME_NOT_FOUND + 1);
                  throw new GameNotFoundException(ERR_GAME_NOT_FOUND, 1);
         }
}
