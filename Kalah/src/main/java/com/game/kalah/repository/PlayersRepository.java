package com.game.kalah.repository;

import com.game.kalah.domain.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Child Object f Game.
 * <strong>exported = false </strong> --> skip exporting a repository, allow
 * creating and updating the player object, while embedded inside the game JSON
 * object. But if <strong>exported = true </strong> which is the default, this will need to insert first the player
 * object then pass its URL in the request of inserting new Game Object as <br>
 * <code>
 * {"boardList":[0,0,0,0,0,0,1,0,0,0,0,0,0,1],
 "status":"PLAYER2TURN",
 "message":"test ",
 "player":[
   {"http://{server:port}/player/1"},
   {"http://{server:port}/player/2"}
   * ]
   * </code>
 * <hr> How to work on player directly: <br>
 * PUT : http://localhost:9090/player/945 { "comment": ["ccc","bbb"],
 * "playerName": "N. Abubakr", "rate": 5 } <br>
 * GET: http://localhost:9090/player/ <br>
 * GET: http://localhost:9090/player/945<br>
 * POST:
 *
 * {
 * "comment": ["aaa","bbb"], "playerName": "Abubakr", "rate": 3 }
 *
 * @author Nesrin
 */
@RepositoryRestResource(exported = false, path = "player", collectionResourceRel = "players")
public interface PlayersRepository extends CrudRepository<Player, Long> {
}
