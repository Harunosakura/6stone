/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.kalah.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.Data;

/**
 *
 * @author Nesrin
 */
@Data
@Entity
public class GameDetails implements Serializable {

         @Id
         private Long id;
         @Lob
         private String description;
         @Lob
         @Basic(fetch = FetchType.LAZY)
         private Byte[]  file;
}
