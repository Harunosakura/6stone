package com.assignment.kalah.service;

import com.assignment.kalah.domain.Game;
import com.assignment.kalah.utils.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Nesrin
 */
public interface ResponseObjectService {
                  static final Logger LOGGER = LoggerFactory.getLogger(ResponseObjectService.class);

          public ResponseObject prepareResponseObject(Game g, boolean start) ;
}
