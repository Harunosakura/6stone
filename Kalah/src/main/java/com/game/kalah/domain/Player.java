/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.kalah.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import lombok.Data;
import org.hibernate.annotations.DiscriminatorFormula;

/**
 *
 * @author Nesrin
 */
@Data
@Entity
@SequenceGenerator(name = "PLAYER_SEQ", initialValue = 1, allocationSize = 1)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "gender", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("N") // to avoid (Using default @DiscriminatorValue for a discriminator of type CHAR is not safe)

//@DiscriminatorFormula("case when gender is not null then 1 else 2 end ")
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
