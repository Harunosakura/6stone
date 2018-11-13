/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.kalah.repository;

import com.game.kalah.domain.Game;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Nesrin
 */
public interface GameRepository extends CrudRepository<Game, Integer> {
}
