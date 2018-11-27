package com.game.kalah.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * @author Nesrin
 */
@Data
@Entity
public class Fans {
         @Id
         @GeneratedValue(strategy = GenerationType.AUTO)
         private Long Id;
         private String fanName;
         private boolean favourite;
         private String review;
         
         @ManyToOne(fetch = FetchType.LAZY)
         @JoinColumn(name="game_id")
         private Game game;
         }
