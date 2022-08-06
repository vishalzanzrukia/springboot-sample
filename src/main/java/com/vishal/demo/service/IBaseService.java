package com.vishal.demo.service;

import com.vishal.demo.enitity.AbstractEntity;

import java.util.List;

public interface IBaseService<T extends AbstractEntity> {

    void remove(int id);

    List<T> findAll();
}
