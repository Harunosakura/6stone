package com.game.kalah.controller;

import com.game.kalah.dto.GameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import com.game.kalah.service.GameInProgressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * <hr>
 * https://www.javacodegeeks.com/2017/08/difference-restcontroller-controller-annotation-spring-mvc-rest.html
 * <hr>
 * The @RestController annotation in Spring MVC is nothing but a combination of
 * the @Controller and the @ResponseBody annotation. It was added into Spring
 * 4.0 to make the development of RESTful Web Services in Spring framework
 * easier. If you are familiar with the REST web services you know that the
 * fundamental difference between a web application and a REST API is that the
 * response from a web application is a generally view of HTML + CSS +
 * JavaScript while REST API just return data in form of JSON or XML. This
 * difference is also obvious in the @Controller and the @RestController
 * annotation. The job of the @Controller is to create a Map of model object and
 * find a view but the @RestController simply returns the object and object data
 * is directly written into HTTP response as JSON or XML.
 *
 * This can also be done with the traditional @Controller and the use of the
 *
 * @ResponseBody annotation but since this is the default behavior of RESTful
 * Web services, Spring introduced @RestController which combined the behavior
 * of @Controller and @ResponseBody together.
 *
 *
 * @author Nesrin
 *
 */
@RestController
public class GameInProgressController {

         @Autowired
         private GameInProgressService gameService;

         /**
          * This service end-point makes a play of the game and change the game
          * state according the selected pit by a player.
          *
          * ResponseEntity represents an HTTP response, including headers, body,
          * and status. While @ResponseBody puts the return value into the body
          * of the response, ResponseEntity also allows us to add headers and
          * status code.
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
          * <hr>
          * What spring MVC did is? <br>
          * Invoked correct <strong> HttpMessageConverter</strong> Based on
          * <ul>
          * <li> Requested format</li>
          * <li>Jars in classpath</li>
          * </ul>
          * @param gameId
          * @param pitId
          * @return Updated Game object from the move made a player.
          */
         @PutMapping(value = "/games/{gameId}/pit/{pitId}",
                 produces = MediaType.APPLICATION_JSON_VALUE)
         public ResponseEntity<GameDTO> play(
                 @PathVariable Integer gameId, @PathVariable Integer pitId) {

                  return new ResponseEntity<>(gameService.play(gameId, pitId),
                          HttpStatus.ACCEPTED);
         }
}
