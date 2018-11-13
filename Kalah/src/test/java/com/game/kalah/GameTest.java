package com.game.kalah;

import com.game.kalah.domain.Game;
import com.game.kalah.utils.Constants;
import com.game.kalah.utils.Status;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Nesrin
 */
public class GameTest {

         public Game game;
         private Integer[] initialBoard;
         public String serverAddress;
         public String url;

         @Value("${server.port}")
         public int port;

         @Before
         public void setup() {
                  initialBoard = new Integer[]{6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0};
                  game = new Game(Arrays.asList(initialBoard),
                          Status.PLAYER1TURN, Constants.START_MESSAGE);
                  game.setId(1);
                  try {
                           this.serverAddress = InetAddress.getLocalHost().getHostAddress();
                  } catch (UnknownHostException ex) {
                           ex.getMessage();
                  }
                  url = "http://" + serverAddress + ":" + port + "/games";
         }

         public Map prepareMapOfBoard(List<Integer> board) {
                  Map<Integer, Integer> boardToMap = new HashMap<>();
                  for (int i = 0; i < board.size(); i++)
                           boardToMap.put(i + 1, board.get(i));
                  return boardToMap;
         }

}
