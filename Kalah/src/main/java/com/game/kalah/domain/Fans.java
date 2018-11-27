/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.kalah.domain;

import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
         private Long id;
         private String fanName;
         @ElementCollection
         private List<String> comment;
         private Integer rate;

}
