/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.kalah.repository;

import com.assignment.kalah.domain.Game;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Nesrin
 */
public interface GameRepository extends CrudRepository<Game, Integer> {
}
