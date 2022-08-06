package com.vishal.demo.enitity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "client")
@Getter
public class Client extends AbstractEntity {

    @Column(nullable = false)
    private String name;
}
