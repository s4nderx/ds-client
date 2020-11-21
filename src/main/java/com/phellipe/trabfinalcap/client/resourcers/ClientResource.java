package com.phellipe.trabfinalcap.client.resourcers;

import com.phellipe.trabfinalcap.client.dto.ClientDTO;
import com.phellipe.trabfinalcap.client.entities.Client;
import com.phellipe.trabfinalcap.client.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
        ClientDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

}
