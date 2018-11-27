package com.game.kalah.repository;

import com.game.kalah.domain.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * PUT: http://localhost:9090/game/33 : to update current object<br>
 * OR<br>
 * POST: http://localhost:9090/game/ : to create new object<br>
 * 
 * {"boardList":[0,0,0,0,0,0,1,0,0,0,0,0,0,1],
 "status":"PLAYER2TURN",
 "message":"test ",
 "player":[
   {"comment":["qqq","qqq"],"playerName":"NNN","rate":1},
   {"comment":["ddd","vvv"],"playerName":"AAA","rate":2},
   {"comment":["uuu","iii"],"playerName":"BBB","rate":3}
 ]}
 *<br>
 * GET: http://localhost:9090/game/ : to get all
 * <br>
 * GET: http://localhost:9090/game/33 : to get the game object with id 33
 * 
 * @author Nesrin
 */
@RepositoryRestResource(path = "game", collectionResourceRel =  "game")
public interface GameRepository extends CrudRepository<Game, Long> {
}
