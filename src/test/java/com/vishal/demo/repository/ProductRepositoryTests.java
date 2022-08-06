package com.vishal.demo.repository;

import com.vishal.demo.enitity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testFindByIsDeletedFalse() {
        List<Product> products = repository.findByIsDeletedFalse();
        Assertions.assertEquals(3, products.size());
    }

    @Test
    public void testFindAll() {
        List<Product> products = repository.findAll();
        Assertions.assertEquals(5, products.size());
    }

    @Test
    @Transactional
    public void testDeleteById() {
        repository.deleteById(1);
        Assertions.assertEquals(5, repository.findAll().size());
        Assertions.assertEquals(2, repository.findByIsDeletedFalse().size());

        //resetting data so other tests will work as expected
        String query = "update product set is_deleted = false where id = 1";
        entityManager.createNativeQuery(query).executeUpdate();
    }
}
