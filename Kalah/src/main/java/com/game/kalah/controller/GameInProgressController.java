package com.game.kalah.controller;

import com.game.kalah.dto.GameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import com.game.kalah.service.GameInProgressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This class exposes RESTFul end-points which are used to by client to
 * communicate with service.
 * https://stackoverflow.com/questions/32221090/org-springframework-web-bind-missingservletrequestparameterexception-required/32221290
 * You are expecting userId as request parameter on server side, but you are
 * sending userId as request body in your client http post request,
 *
 * Change the client side to send userId as request parameter
 *
 * $http.post('/portal/settings/user/deletecustomeruser?userId='+userId) Or
 * Change server side to use userId as request body in the controller
 *
 * myControllerMethod (@RequestBody String userId)
 *
 * @author Nesrin
 *
 */
@Controller
public class GameInProgressController {

         @Autowired
         private GameInProgressService gameService;

         /**
          * This service end-point makes a play of the game and change the game
          * state according the selected pit by a player.
          *
          * https://stackoverflow.com/questions/37471005/jsonmappingexception-can-not-deserialize-instance-of-java-lang-integer-out-of-s
          *
          * down vote accepted Obviously Jackson can not deserialize the passed
          * JSON into an Integer. If you insist to send a JSON representation of
          * a User through the request body, you should encapsulate the userId
          * in another bean like the following:
          *
          * public class User { private Integer userId; // getters and setters }
          * Then use that bean as your handler method argument:
          *
          * @RequestMapping(...) public @ResponseBody Record
          * getRecord(@RequestBody User user) { ... } If you don't like the
          * overhead of creating another bean, you could pass the userId as part
          * of Path Variable, e.g. /getuser/15. In order to do that:
          *
          * @RequestMapping(value = "/getuser/{userId}", method = POST, produces
          * = "application/json") public @ResponseBody Record
          * getRecord(@PathVariable Integer userId) { ... } Since you no longer
          * send a JSON in the request body, you should remove that consumes
          * attribute.
          *
          * @param gameId
          * @param pitId
          * @return Updated Game object from the move made a player.
          */
         @PostMapping(value = "/games/{gameId}/pit/{pitId}",
                 produces = MediaType.APPLICATION_JSON_VALUE)
         public ResponseEntity<GameDTO> play(
                 @PathVariable Integer gameId, @PathVariable Integer pitId) {

                  return new ResponseEntity<>(gameService.play(gameId, pitId),
                          HttpStatus.ACCEPTED);
         }
}
