package com.game.kalah.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.Data;

/**
 *
 * @author Nesrin
 */
@Data
@Entity
@SequenceGenerator(name = "FANS_SEQ", initialValue = 1, allocationSize = 1)
public class Fans  implements Serializable{

         @Id
         @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FANS_SEQ")
         private Long Id;
         private String fanName;
         private boolean favourite;
         private String review;

         @ManyToOne(fetch = FetchType.LAZY)
         @JoinColumn(name = "game_id")
         private Game game;
}
