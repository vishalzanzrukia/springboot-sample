package com.vishal.demo.enitity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Base entity which needs to be extended by all entities
 */
@MappedSuperclass
@Getter
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "modified_date", nullable = false)
    private LocalDateTime modifiedDate;
}
