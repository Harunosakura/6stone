package com.game.kalah.service;

import com.game.kalah.domain.Game;
import com.game.kalah.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Nesrin
 */
public interface ResponseService {

         static final Logger LOGGER = LoggerFactory.getLogger(ResponseService.class);

         public ResponseDTO prepareResponseObject(Game g, boolean start);
}
