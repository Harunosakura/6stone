package com.game.kalah.dto;

import com.game.kalah.domain.Game;
import java.util.Map;

/**
 *
 * @author Nesrin
 */
public class GameReportDTO {

         private Integer id;
         private Map<Integer ,Integer> board;
         private String status;
         private String message;

         public GameReportDTO(Game g) {
         }


         @Override
         public String toString() {
                  return "Game [id=" + id
                          + ", status=" + status
                          + ", message=" + message
                          + ", board[" + board + "]]";
         }

}
