package com.game.kalah.domain;

import com.game.kalah.utils.CollectionUtils;
import com.game.kalah.utils.Status;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Nesrin
 */
@Entity
public class Game {

         @Id
         @GeneratedValue(strategy = GenerationType.AUTO)
         private Integer id;
         @ElementCollection
         @Column(name = "pits")
         private List<Integer> boardList;
         private Status status;
         private String message;

         public Game() {
         }

         public Game(String message) {
                  this.boardList = Arrays.asList(new Integer[]{6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0});
                  this.status = Status.PLAYER1TURN;
                  this.message = message;
         }

         /**
          * @return the id
          */
         public Integer getId() {
                  return id;
         }

         /**
          * @param id
          */
         public void setId(Integer id) {
                  this.id = id;
         }

         /**
          * @return the board
          */
         public List<Integer> getBoardList() {
                  return boardList;
         }

         /**
          * @param boardList the board to set
          */
         public void setBoardList(List<Integer> boardList) {
                  this.boardList = boardList;

         }

         /**
          * @return the status
          */
         public Status getStatus() {
                  return status;
         }

         /**
          * @param status the status to set
          */
         public void setStatus(Status status) {
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
         public boolean equals(Object o) {
                  // If the object is compared with itself then return true   
                  if (o == this)
                           return true;

                  /* Check if o is an instance of Complex or not "null instanceof [type]" also returns false */
                  if (!(o instanceof Game))
                           return false;

                  // typecast o to Complex so that we can compare data members  
                  Game c = (Game) o;

                  return Objects.equals(c.getId(), id)
                          && c.getMessage().equals(message)
                          && c.getStatus().equals(status)
                          && CollectionUtils.sameElements(c.getBoardList(), boardList);
         }

         @Override
         public String toString() {
                  String game = "Game [id=" + id
                          + ", status=" + status
                          + ", message=" + message
                          + ", board[" + boardList + "]]";
                  return game;
         }

}
