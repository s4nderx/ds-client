package com.phellipe.trabfinalcap.client.services;

import com.phellipe.trabfinalcap.client.dto.ClientDTO;
import com.phellipe.trabfinalcap.client.entities.Client;
import com.phellipe.trabfinalcap.client.repositories.ClientRepository;
import com.phellipe.trabfinalcap.client.services.exceptions.ResourceNotFoundException;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    @Transactional(rollbackFor = Exception.class)
    public ClientDTO create(ClientDTO dto){
        Client entity = new Client();
        transformDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
        Page<Client> list = repository.findAll(pageRequest);
        return list.map(ClientDTO::new);
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Optional<Client> obj = repository.findById(id);
        Client entity= obj.orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        return new ClientDTO(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public ClientDTO update(Long id, ClientDTO dto) {
        try {
            Client entity = repository.getOne(id);
            transformDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ClientDTO(entity);
        }catch (EntityNotFoundException ex){
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    private void transformDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setBirthDate(dto.getBirthDate());
        entity.setCpf(dto.getCpf());
        entity.setChildren(dto.getChildren());
        entity.setIncome(dto.getIncome());
    }



}
