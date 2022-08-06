package com.vishal.demo.service;

import com.vishal.demo.enitity.AbstractEntity;
import com.vishal.demo.exception.NotFoundHttpStatusException;
import com.vishal.demo.repository.IBaseRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * The base service which should be extended by all services
 *
 * @param <T> the entity type associated with the service
 */
@Slf4j
public abstract class AbstractService<T extends AbstractEntity> implements IBaseService<T> {

    abstract IBaseRepository<T> getRepository();

    @Override
    public void remove(int id) {
        if (getRepository().findById(id).isEmpty()) {
            var message = String.format("The entity with id %s not found", id);
            log.warn(message);
            throw new NotFoundHttpStatusException(message);
        }
        getRepository().deleteById(id);
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }
}
