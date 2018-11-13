package com.game.kalah.service.impl;

import com.game.kalah.GameTest;
import com.game.kalah.dto.ResponseDTO;
import com.game.kalah.utils.Constants;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.game.kalah.service.ResponseService;

/**
 *
 * @author Nesrin
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ResponseObjectSeviceTest extends GameTest {

//         @Autowired
//         private ResponseService rosMock;
//
//         @Before
//         public void localSetup() {
//                  url = "http://" + serverAddress + ":" + port + "/games";
//
//         }
//
//         @Test
//         public void testPrepareResponseObject_game_first_call() {
//                  ResponseDTO prepareResponseObject = rosMock.prepareResponseObject(game, true);
//                  assertEquals(Integer.valueOf(1), prepareResponseObject.getId());
//                  assertEquals(Constants.START_MESSAGE + 1, prepareResponseObject.getMessage());
//                  assertEquals(url + "/" + 1, prepareResponseObject.getUri());
//                  assertEquals(null, prepareResponseObject.getUrl());
//                  assertEquals(null, prepareResponseObject.getStatus());
//
//         }
//
//         @Test
//         public void testPrepareResponseObject_game_in_progress() {
//                  ResponseDTO prepareResponseObject = rosMock.prepareResponseObject(game, false);
//                  assertEquals(Integer.valueOf(1), prepareResponseObject.getId());
//                  assertEquals(Constants.START_MESSAGE + 1, prepareResponseObject.getMessage());
//                  assertEquals(url + "/" + 1, prepareResponseObject.getUrl());
//                  assertEquals(null, prepareResponseObject.getUri());
//                  assertEquals(prepareMapOfBoard(game.getBoardList()), prepareResponseObject.getStatus());
//
//         }
}
