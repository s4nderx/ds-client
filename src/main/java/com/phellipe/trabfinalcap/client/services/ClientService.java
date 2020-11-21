package com.phellipe.trabfinalcap.client.services;

import com.phellipe.trabfinalcap.client.dto.ClientDTO;
import com.phellipe.trabfinalcap.client.entities.Client;
import com.phellipe.trabfinalcap.client.repositories.ClientRepository;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    @Transactional
    public ClientDTO findById(Long id){
        Optional<Client> obj = repository.findById(id);
        Client entity= obj.get();
        return new ClientDTO(entity);
    }

}
