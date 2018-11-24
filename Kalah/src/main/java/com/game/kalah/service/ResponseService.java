package com.game.kalah.service;

import com.game.kalah.domain.Game;
import com.game.kalah.dto.GameDTO;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Nesrin
 */
public interface ResponseService {

         public GameDTO prepareResponseObject(Game g, boolean start);

         public Map<String, Object> prepareResponseObjectForAll(List<Game> g);
}
