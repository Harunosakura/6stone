package com.assignment.kalah.service.impl;

import com.assignment.kalah.domain.Game;
import com.assignment.kalah.utils.ResponseObject;
import com.assignment.kalah.service.ResponseObjectService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * ResponseObjectServiceImpl Class is responsible for creating the response
 * object
 *
 * @author Nesrin
 */
@Service
public class ResponseObjectServiceImpl implements ResponseObjectService {

         private String serverAddress;

         @Value("${server.port}")
         private String serverPort;

         public ResponseObjectServiceImpl() {
                  try {
                           this.serverAddress = InetAddress.getLocalHost().getHostAddress();
                  } catch (UnknownHostException ex) {
                           LOGGER.error(ex.getMessage());
                  }
         }

         /**
          *
          * @param g is the game object
          * @param start is a boolean parameter, true is case of creating a new
          * game, and false in case of in progress game
          * @return {@link ResponseObject} which is the object displayed as JSON
          * object
          */
         @Override
         public ResponseObject prepareResponseObject(Game g, boolean start) {
                  ResponseObject response = new ResponseObject();
                  response.setId(g.getId());
                  response.setMessage(g.getMessage() + g.getId());
                  if (start)
                           response.setUri(getURI(g.getId()));
                  else {
                           response.setUrl(getURI(g.getId()));
                           response.setStatus(g.getBoardList());
                  }

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
