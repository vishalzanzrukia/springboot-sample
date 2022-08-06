package com.vishal.demo.enitity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product")
//this annotation will help to update when we perform actually delete
@SQLDelete(sql = "UPDATE product SET is_deleted = true WHERE id=?")
@Getter
public class Product extends AbstractEntity {

    @Column(nullable = false)
    @JsonInclude
    private String name;

    @Column(name = "is_deleted", nullable = false)
    @JsonIgnore
    private boolean isDeleted;
}
