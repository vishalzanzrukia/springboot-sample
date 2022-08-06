package com.vishal.demo.repository;

import com.vishal.demo.enitity.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
public class ClientRepositoryTests {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testFindAll() {
        List<Client> clients = repository.findAll();
        Assertions.assertEquals(5, clients.size());
    }

    @Test
    @Transactional
    public void testDeleteById() {
        repository.deleteById(1);
        Assertions.assertEquals(4, repository.findAll().size());

        //resetting data so other tests will work as expected
        String query = "insert into client(id, name) values(1, 'client-1')";
        entityManager.createNativeQuery(query).executeUpdate();
    }
}
