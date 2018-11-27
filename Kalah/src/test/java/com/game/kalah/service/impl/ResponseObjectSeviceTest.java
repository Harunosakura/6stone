package com.game.kalah.service.impl;

import com.game.kalah.GameTest;
import com.game.kalah.domain.Game;
import com.game.kalah.dto.GameDTO;
import static com.game.kalah.utils.Constants.*;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.game.kalah.service.ResponseService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Nesrin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ResponseObjectSeviceTest extends GameTest {

         @Autowired
         private ResponseService rosMock;

         private Game testGame;
         public String serverAddress;
         public String url;

         @Value("${server.port}")
         public int port;

         @Before
         public void setup() {
                  testGame = new Game(START_MESSAGE);
                  testGame.setId(1l);
                  try {
                           this.serverAddress = InetAddress.getLocalHost().getHostAddress();
                  } catch (UnknownHostException ex) {
                           ex.getMessage();
                  }
                  url = "http://" + serverAddress + ":" + port + "/games";
         }

         @Test
         public void testPrepareResponseObject_game_first_call() {
                  GameDTO prepareResponseObject = rosMock.prepareResponseObject(testGame, true);
                  assertEquals(Long.valueOf(1), prepareResponseObject.getId());
                  assertEquals(START_MESSAGE + 1, prepareResponseObject.getMessage());
                  assertEquals(url + "/" + 1, prepareResponseObject.getUri());
                  assertEquals(null, prepareResponseObject.getUrl());

         }

         @Test
         public void testPrepareResponseObject_game_in_progress() {
                  GameDTO prepareResponseObject = rosMock.prepareResponseObject(testGame, false);
                  assertEquals(Long.valueOf(1), prepareResponseObject.getId());
                  assertEquals(START_MESSAGE + 1, prepareResponseObject.getMessage());
//                  assertEquals(url + "/" + 1, prepareResponseObject.getUrl());
                  assertEquals(null, prepareResponseObject.getUri());

         }
}
