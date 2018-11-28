/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.kalah.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.Data;

/**
 *
 * @author Nesrin
 */
@Data
@Entity
@SequenceGenerator(name = "PLAYER_SEQ", initialValue = 1, allocationSize = 1)

public class Player  implements Serializable{

         @Id
         @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAYER_SEQ")
         private Long id;
         private String playerName;
         @ElementCollection
         private List<String> comment;
         private Integer rate;
         private boolean status;

}
