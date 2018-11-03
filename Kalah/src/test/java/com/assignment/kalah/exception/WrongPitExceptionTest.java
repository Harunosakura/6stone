package com.assignment.kalah.exception;

import com.assignment.kalah.exception.WrongPitException;
import com.assignment.kalah.utils.Constants;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Nesrin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WrongPitExceptionTest {

         @Rule
         public ExpectedException thrown = ExpectedException.none();

         @Test
         public void throwsWrongPitException_with_message() {
                  thrown.expect(WrongPitException.class);
                  thrown.expectMessage(Constants.ERR_EMPTY_PIT);
                  throw new WrongPitException("Selected pit is empty! Choose another one");
         }

}
