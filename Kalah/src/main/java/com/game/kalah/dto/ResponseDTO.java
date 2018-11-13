package com.game.kalah.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nesrin
 */
public class ResponseDTO {

         private Integer id;
         private String uri;
         private String url;
         private String message;
         private Map<Integer, Integer> status;

         /**
          * @return the id
          */
         public Integer getId() {
                  return id;
         }

         /**
          * @param id the id to set
          */
         public void setId(Integer id) {
                  this.id = id;
         }

         /**
          * @return the status
          */
         public Map<Integer, Integer> getStatus() {
                  return status;
         }

         /**
          * @param board
          */
         public void setStatus(List<Integer> board) {
                  status = new HashMap<>();
                  for (int i = 0; i < board.size(); i++)
                           getStatus().put(i + 1, board.get(i));
         }

         /**
          * @return the uri
          */
         public String getUri() {
                  return uri;
         }

         /**
          * @param uri the uri to set
          */
         public void setUri(String uri) {
                  this.uri = uri;
         }

         /**
          * @return the url
          */
         public String getUrl() {
                  return url;
         }

         /**
          * @param url the url to set
          */
         public void setUrl(String url) {
                  this.url = url;
         }

         /**
          * @return the message
          */
         public String getMessage() {
                  return message;
         }

         /**
          * @param message the message to set
          */
         public void setMessage(String message) {
                  this.message = message;
         }

}
