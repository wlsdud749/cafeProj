package com.example.cafe.entity.listener;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Slf4j
public class LibraryEntityListener {

    @PrePersist
    public void prePersist(Object o) {
        log.info("prePersist ------------------------------- ");
        if(o instanceof DateListener) {
            ((DateListener)o).setCreatedAt(LocalDateTime.now());
            ((DateListener)o).setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object o) {
        log.info("preUpdate ------------------------------- ");
        if(o instanceof DateListener) {
            ((DateListener)o).setUpdatedAt(LocalDateTime.now());
        }
    }
}
