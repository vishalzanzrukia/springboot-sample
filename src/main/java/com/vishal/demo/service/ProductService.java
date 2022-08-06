package com.vishal.demo.service;

import com.vishal.demo.enitity.Product;
import com.vishal.demo.repository.ProductRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class ProductService extends AbstractService<Product> {

    @Autowired
    @Getter
    private ProductRepository repository;

    /***
     * Method to retrieve list of products, if includeDeleted true, it will return all produced including soft deleted ones,
     * otherwise this will only return non soft deleted ones
     *
     * @param includeDeleted the flag to define whether to return all entities including soft deleted or exclude soft deleted
     * @return the list of products
     */
    public List<Product> findAll(boolean includeDeleted) {
        if (includeDeleted) {
            log.info("All products are included (with soft deleted ones as well) in the result set");
            //we need all records included "soft" deleted
            return findAll();
        }

        log.info("Soft deleted products are excluded from result set");
        //we need records which are not "soft" deleted
        return repository.findByIsDeletedFalse();
    }
}
