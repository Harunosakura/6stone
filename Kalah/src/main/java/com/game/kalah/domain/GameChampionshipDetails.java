/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.kalah.domain;

import java.time.LocalDateTime;
import javax.persistence.Embeddable;
import javax.persistence.Version;
import lombok.Data;

/**
 *
 * @author Nesrin
 */
@Data
@Embeddable
public class GameChampionshipDetails {
         
         //@Basic
         private String championshipDescription;
//         @Basic
         private Integer championshipEveryYear;
         

}
