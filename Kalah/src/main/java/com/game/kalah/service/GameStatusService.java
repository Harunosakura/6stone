/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.kalah.service;

import com.game.kalah.domain.Game;
import com.game.kalah.dto.GameDTO;
import com.game.kalah.exception.GameNotFoundException;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Nesrin
 */
public interface GameStatusService {

         Game getGameById(Long gId) throws GameNotFoundException;

         Map<String, Object> getAllGames();

         GameDTO createNewGame();

}
