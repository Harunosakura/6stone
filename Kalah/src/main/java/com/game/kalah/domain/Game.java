package com.game.kalah.domain;

import static com.game.kalah.utils.CollectionUtils.*;
import com.game.kalah.utils.Status;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

/**
 *
 * @author Nesrin
 */
@Data
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
                          && sameElements(c.getBoardList(), boardList);
         }

         @Override
         public int hashCode() {
                  int hash = 7;
                  hash = 29 * hash + Objects.hashCode(this.id);
                  hash = 29 * hash + Objects.hashCode(this.boardList);
                  hash = 29 * hash + Objects.hashCode(this.status);
                  hash = 29 * hash + Objects.hashCode(this.message);
                  return hash;
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
