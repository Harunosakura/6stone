package com.game.kalah.service.impl;

import com.game.kalah.domain.Game;
import com.game.kalah.dto.GameDTO;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.game.kalah.service.ResponseService;
import lombok.extern.slf4j.Slf4j;

/**
 * ResponseServiceImpl Class is responsible for creating the response object
 *
 * @author Nesrin
 */
@Service
@Slf4j
public class ResponseServiceImpl implements ResponseService {

         private String serverAddress;

         @Value("${server.port}")
         private String serverPort;

         public ResponseServiceImpl() {
                  try {
                           this.serverAddress = InetAddress.getLocalHost().getHostAddress();
                  } catch (UnknownHostException ex) {
                           log.error(ex.getMessage());
                  }
         }

         /**
          *
          * @param g is the game object
          * @param start is a boolean parameter, true is case of creating a new
          * game, and false in case of in progress game
          * @return {@link ResponseDTO} which is the object displayed as JSON
          * object
          */
         @Override
         public GameDTO prepareResponseObject(Game g, boolean start) {
                  GameDTO response = new GameDTO(g);
                  if (start)
                           response.setUri(getURI(g.getId()));
                  else
                           response.setUrl(getURI(g.getId()));

                  return response;
         }

         /**
          * prepare the URL required to submit moves and create new game with
          * the ID f the game
          *
          * @param gameId to be concatenated to the URL
          * @return String object representing the path to the game from browser
          */
         private String getURI(int gameId) {
                  return "http://" + serverAddress + ":" + serverPort + "/games/" + gameId;
         }

}
