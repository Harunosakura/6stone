package com.game.kalah.dto;

import com.game.kalah.domain.Game;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 *
 * @author Nesrin
 */
@Data
public class GameDTO {

         private Integer id;
         private Map<Integer, Integer> board;
         private String player;
         private String message;
         private String uri;
         private String url;

         public GameDTO(Game g) {
                  this.id = g.getId();
                  this.message = g.getMessage() + g.getId();
                  this.player = "PLAYER1TURN".equals(g.getStatus().name()) ? "1" : "2";
                  board = new HashMap<>();
                  for (int i = 0; i < g.getBoardList().size(); i++)
                           board.put(i + 1, g.getBoardList().get(i));

         }

         @Override
         public String toString() {
                  return "Game [id=" + getId()
                          + ", player=" + getPlayer()
                          + ", message=" + getMessage()
                          + ", board[" + getBoard() + "]]";
         }

}
