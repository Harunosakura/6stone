/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.kalah.service;

import com.game.kalah.domain.Game;
import com.game.kalah.exception.GameNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Nesrin
 */
public interface GameStatusService {

         static final Logger LOGGER = LoggerFactory.getLogger(GameStatusService.class);

         public Game getGameById(Integer gId) throws GameNotFoundException;

}
