/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.kalah.utils;

import java.util.List;

/**
 *
 * @author Nesrin
 */
public class CollectionUtils {

         public static boolean sameElements(List firstList, List secondList) {

                  // The size hould be the same, otherwise stop.
                  if (firstList.size() != secondList.size())
                           return false;

                  // Iterate over the elements of the first list.
                  for (int index = 0; index < firstList.size(); index++)
                           // Check if the element is also in the second list.
                           if (!secondList.contains(firstList.get(index)))
                                    return false;

                  // They heve the same elements.
                  return true;

         }
}
