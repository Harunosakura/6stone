/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.kalah.repository;

import com.game.kalah.domain.Fans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * PUT : http://localhost:9090/fans/945 { "comment": ["ccc","bbb"], "fanName":
 * "N. Abubakr", "rate": 5 } GET: http://localhost:9090/fans/ GET:
 * http://localhost:9090/fans/945 POST:
 *
 * {
 * "comment": ["aaa","bbb"], "fanName": "Abubakr", "rate": 3 }
 *
 * @author Nesrin
 */
@RepositoryRestResource(exported = false, path = "fans", collectionResourceRel = "fans")
public interface FansRepository extends CrudRepository<Fans, Long> {
}
