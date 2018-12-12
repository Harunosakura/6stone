package com.game.kalah.domain;

import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import lombok.Data;

/**
 *
 * @author Nesrin
 */
@Data
@MappedSuperclass // means don't map to DB and can be inherited
public class BaseEntity {
         
         @Version
         private LocalDateTime timeStamp;
}
