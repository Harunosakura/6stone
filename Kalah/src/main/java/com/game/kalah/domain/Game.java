package com.game.kalah.domain;

import static com.game.kalah.utils.CollectionUtils.*;
import com.game.kalah.utils.Status;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

/**
 * Adding <strong>@XmlRootElement</strong> force default response body to be XML
 * instead of JSON
 *
 * To change it in the browser to JSON just add ".json" to the URL <br>
 * To change it in the browser to XML just add ".xml" to the URL <br>
 *
 * Inside the testing tool used in the browser add "Accepted application/xml" or
 * "Accepted application/json" in the header section
 *
 * @author Nesrin
 */
//@XmlRootElement  //from javax.xml.bind.annotation.XmlRootElement
@Data
@Entity

public class Game {

         @Id
         @GeneratedValue(strategy = GenerationType.AUTO)
         private Long id;
         @ElementCollection
         @Column(name = "pits")
         private List<Integer> boardList;
         private Status status;
         private String message;
         
         // CascadeType.ALL is important to save object of nested Fans in JSON request
         // before saving game data, and flush the database too.
         @OneToMany(cascade = {CascadeType.ALL})
         private List<Fans> fans;

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

                  return Objects.equals(c.getId(), getId())
                          && c.getMessage().equals(getMessage())
                          && c.getStatus().equals(getStatus())
                          && sameElements(c.getBoardList(), getBoardList());
         }

         @Override
         public int hashCode() {
                  int hash = 7;
                  hash = 29 * hash + Objects.hashCode(this.getId());
                  hash = 29 * hash + Objects.hashCode(this.getBoardList());
                  hash = 29 * hash + Objects.hashCode(this.getStatus());
                  hash = 29 * hash + Objects.hashCode(this.getMessage());
                  return hash;
         }

         @Override
         public String toString() {
                  String game = "Game [id=" + getId()
                          + ", status=" + getStatus()
                          + ", message=" + getMessage()
                          + ", board[" + getBoardList() + "]]";
                  return game;
         }

}
