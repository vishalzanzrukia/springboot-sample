package com.vishal.demo.service;

import com.vishal.demo.enitity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
public class ProductServiceTests {

    @Autowired
    private ProductService service;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testFindAll() {
        List<Product> products = service.findAll();
        Assertions.assertEquals(5, products.size());
    }

    @Test
    @Transactional
    public void testDeleteById() {
        service.remove(1);
        Assertions.assertEquals(2, service.findAll(false).size());
        Assertions.assertEquals(5, service.findAll(true).size());

        //resetting data so other tests will work as expected
        String query = "update product set is_deleted = false where id = 1";
        entityManager.createNativeQuery(query).executeUpdate();
    }
}
