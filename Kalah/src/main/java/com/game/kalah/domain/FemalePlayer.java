/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.kalah.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Nesrin
 */
@Entity
@DiscriminatorValue("F")
public class FemalePlayer extends Player {
         
}
