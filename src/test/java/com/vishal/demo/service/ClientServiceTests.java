package com.vishal.demo.service;

import com.vishal.demo.enitity.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
public class ClientServiceTests {

    @Autowired
    private ClientService service;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testFindAll() {
        List<Client> clients = service.findAll();
        Assertions.assertEquals(5, clients.size());
    }

    @Test
    @Transactional
    public void testDeleteById() {
        service.remove(1);
        Assertions.assertEquals(4, service.findAll().size());

        //resetting data so other tests will work as expected
        String query = "insert into client(id, name) values(1, 'client-1')";
        entityManager.createNativeQuery(query).executeUpdate();
    }
}
