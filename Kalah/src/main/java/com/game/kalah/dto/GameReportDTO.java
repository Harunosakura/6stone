package com.game.kalah.dto;

import com.game.kalah.domain.Game;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Nesrin
 */
public class GameReportDTO {

         private Integer id;
         private Map<Integer, Integer> board;
         private String status;
         private String message;

         public GameReportDTO(Game g) {
                  this.id = g.getId();
                  this.message = g.getMessage() + g.getId();
                  this.status = "PLAYER1TURN".equals(g.getStatus().name()) ? "1" : "2";
                  board = new HashMap<>();
                  for (int i = 0; i < g.getBoardList().size(); i++)
                           board.put(i + 1, g.getBoardList().get(i));

         }

         /**
          * @return the id
          */
         public Integer getId() {
                  return id;
         }

         /**
          * @param id the id to set
          */
         public void setId(Integer id) {
                  this.id = id;
         }

         /**
          * @return the board
          */
         public Map<Integer, Integer> getBoard() {
                  return board;
         }

         /**
          * @param board the board to set
          */
         public void setBoard(Map<Integer, Integer> board) {
                  this.board = board;
         }

         /**
          * @return the status
          */
         public String getStatus() {
                  return status;
         }

         /**
          * @param status the status to set
          */
         public void setStatus(String status) {
                  this.status = status;
         }

         /**
          * @return the message
          */
         public String getMessage() {
                  return message;
         }

         /**
          * @param message the message to set
          */
         public void setMessage(String message) {
                  this.message = message;
         }

         @Override
         public String toString() {
                  return "Game [id=" + getId()
                          + ", status=" + getStatus()
                          + ", message=" + getMessage()
                          + ", board[" + getBoard() + "]]";
         }

}
