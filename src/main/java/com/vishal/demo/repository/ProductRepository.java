package com.vishal.demo.repository;

import com.vishal.demo.enitity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends IBaseRepository<Product> {
    List<Product> findByIsDeletedFalse();
}
