/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.kalah.repository;

import com.game.kalah.domain.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * PUT: http://localhost:9090/game/33
 * 
 * {"boardList":[0,0,0,0,0,0,1,0,0,0,0,0,0,1],
 "status":"PLAYER2TURN",
 "message":"test ",
 "id":33,
 "fans":[
   {"comment":["qqq","qqq"],"fanName":"NNN","rate":1},
   {"comment":["ddd","vvv"],"fanName":"AAA","rate":2},
   {"comment":["uuu","iii"],"fanName":"BBB","rate":3}
 ]}
 *
 * @author Nesrin
 */
@RepositoryRestResource(path = "game", collectionResourceRel =  "game")
public interface GameRepository extends CrudRepository<Game, Long> {
}
