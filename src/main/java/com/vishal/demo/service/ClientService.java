package com.vishal.demo.service;

import com.vishal.demo.enitity.Client;
import com.vishal.demo.repository.ClientRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientService extends AbstractService<Client> {

    @Autowired
    @Getter
    private ClientRepository repository;
}
