package com.game.kalah.repository;

import com.game.kalah.domain.Fans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * PUT: http://localhost:9090/gamefan/33 : to update current object<br>
 * OR<br>
 * POST: http://localhost:9090/gamefan/ : to create new object<br>
 *
 * {
 * "fanName": "Nesrin", "favourite": true, "review": "I Like It !",
 * "game":"http://localhost:9090/game/5" }
 *
 * @author Nesrin
 */
@RepositoryRestResource(path = "gamefan", collectionResourceRel = "gamefans")
public interface GameFansRepository extends CrudRepository<Fans, Long> {
}
