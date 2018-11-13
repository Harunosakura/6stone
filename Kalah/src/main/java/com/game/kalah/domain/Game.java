package com.game.kalah.domain;

import com.game.kalah.utils.Status;
import java.io.Serializable;
import java.util.List;
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

         public Game(List<Integer> boardList, Status status, String message) {
                  this.boardList = boardList;
                  this.status = status;
                  this.message = message;
         }

         /**
          * @return the id
          */
         public Integer getId() {
                  return id;
         }

         /**
          * @param gameId the id to set
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
         public String toString() {
                  String game = "Game [id=" + id
                          + ", status=" + status
                          + ", message=" + message
                          + ", board[" + boardList + "]]";
                  return game;
         }

}
