package com.game.kalah.domain;

import static com.game.kalah.utils.CollectionUtils.*;
import static com.game.kalah.utils.Constants.*;
import com.game.kalah.utils.Status;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
@Table(name = "game")
@SecondaryTables({
         @SecondaryTable(name = "game_message", pkJoinColumns = {
                  @PrimaryKeyJoinColumn(name = "game_id", referencedColumnName = "id")})
         ,
         @SecondaryTable(name = "another_game_details", uniqueConstraints = {
                  @UniqueConstraint(columnNames = {"alternate_name"})})
})

@SequenceGenerator(name = "GAME_SEQ", initialValue = 1, allocationSize = 1)
/**
 * Serialization Process :- Serialization in java is a mechanism of writing the
 * state of an object into a byte stream.
 *
 * According to JPA Spec:
 *
 * "If an entity instance is to be passed by value as a detached object (e.g.,
 * through a remote interface), the entity class must implement the Serializable
 * interface."
 *
 * "JSR 220: Enterprise JavaBeansTM,Version 3.0 Java Persistence API Version
 * 3.0, Final Release May 2, 2006"
 *
 * When to use it :-
 *
 * when we make any POJO class serialize then whenever we try convert it to make
 * any kind of database understand it changes itself to stream of bits.
 *
 * When we are using Hibernate it is not compulsory to make POJO serialized
 * (because hibernate takes care of it ) but in database transactions we use
 * native SQL and JPA according to our requirement and so it is mention in JPA
 * POJO must be serialized.
 *
 * And so it is always a good practice to make POJO serialize.
 */
public class Game implements Serializable {

         @Id
         @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GAME_SEQ")
         private Long id;
         /**
          * Difference between @Size, @Length and @Column(length=value) when
          * using JPA and Hibernate.
          *
          * @Column is a JPA annotation and the length attribute is used by the
          * schema generation tool to set the associated SQL column length.<br>
          *
          * @Size/@Max/@Min are a <strong>Bean Validation (NOT JPA
          * validation)</strong> annotation that validates that the associated
          * String has a value whose length is bounded by the minimum and
          * maximum values.<br>
          *
          * @Length is a Hibernate-specific annotation and has the same meaning
          * as @Size So both 2. and 3. should validate the String length using
          * Bean Validation. I'd pick 2. because it's generic.
          *
          * @Length/@Max/@Min throws Exception <br>
          * <i> javax.validation.UnexpectedTypeException: HV000030: No validator
          * could be found for constraint
          * 'org.hibernate.validator.constraints.Length' validating type
          * 'java.util.List'. Check configuration for
          * 'boardList' </i>
          */
         @ElementCollection
         @Column(name = "pits", length = 2)
         @Size(max = MAX_NUMBER_OF_PITS, min = MAX_NUMBER_OF_PITS)
         private List<Integer> boardList;

         /*
          *  Fetch type --> is <strong>EAGER</strong> by default : opposite to fetch of @ManyToMany, ..
          * optional is <strong>TRUE</strong> by default : opposite to @Column attribute nullable which is false
          */
         @Basic(fetch = FetchType.EAGER)
         private Status status;
         @Column(table = "game_message")
         @NotBlank // this is for string
         private String message;
         /*
          *  Fetch type --> is <strong>EAGER</strong> by default 
          */
         @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
          @PrimaryKeyJoinColumn
         private GameDetails details;
         @Column(table = "another_game_details", name = "alternate_name")
         private String alternateNames;

         /**
          * Attributes effect.
          * <strong>CascadeType.ALL </strong>is important to save object of
          * nested Player and flush the database too in JSON request before
          * saving game data.<br>
          * <strong>mappedBy = "game" </strong>: for bidirectional relation.
          * When added here, should add both <code> @ManyToOne(fetch = FetchType.LAZY)
          * //         private Game game </code>; relation on player object
          * <strong>NOTE THAT</strong> will face inability to insert the new
          * game object including new players like below sample. <br>
          * <hr>
          * Unidirectional relation is by <br>
          * This sample works fine without need for -<strong>mappedBy = "game"
          * </strong>- attribute, but affect the structure of tables as leads to
          * creation of a table joining ids from game and player. This instead
          * of adding a single column in Player table with name
          * <strong>game_id</strong>
          * as a Forign Key  <br>
          * To avoid effect of creation of intermediate table just add
          *
          * @JoinColumn(name = "game_id")<br>
          * <code>
          * {"boardList":[6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0],
          * "status":"PLAYER1TURN",
          * "message":"New",
          * "players":[
          * {"comment":["I like it","ggh"],"playerName":"QQQ","rate":1},
          * {"comment":["tre","iiu"],"playerName":"Nesrin","rate":2},
          * {"comment":["uqqquu","iiggi"],"playerName":"SSS","rate":3}
          * ]}
          * </code>
          * <strong>orphanRemoval = true</strong>
          * orphanRemoval has nothing to do with ON DELETE CASCADE.<br>
          *
          * orphanRemoval is an entirely ORM-specific thing. It marks "child"
          * entity to be removed when it's no longer referenced from the
          * "parent" entity, e.g. when you remove the child entity from the
          * corresponding collection of the parent entity.<br>
          *
          * ON DELETE CASCADE is a database-specific thing, it deletes the
          * "child" row in the database when the "parent" row is deleted.<br>
          *
          */
         @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
         @JoinColumn(name = "game_id")
         @Size(max = 2)
         private List<Player> players;
         @OneToMany(mappedBy = "game", orphanRemoval = true, cascade = {CascadeType.REMOVE})
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
