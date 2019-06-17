package org.fasttrackit.pizzaonlineapi.web;

import org.fasttrackit.pizzaonlineapi.domain.Client;
import org.fasttrackit.pizzaonlineapi.exception.ResourceNotFoundException;
import org.fasttrackit.pizzaonlineapi.service.ClientService;
import org.fasttrackit.pizzaonlineapi.transfer.client.CreateClientRequest;
import org.fasttrackit.pizzaonlineapi.transfer.client.GetClientRequest;
import org.fasttrackit.pizzaonlineapi.transfer.client.UpdateClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    //endpoint
    @GetMapping
    public ResponseEntity<Page<Client> > getClients(GetClientRequest request, Pageable pageable){
        Page<Client> response = clientService.getClient(request, pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody @Valid CreateClientRequest request){
        Client client = clientService.createClient(request);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity getClient(@PathVariable Long id) throws ResourceNotFoundException {
        Client client = clientService.getClient(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateClient(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateClientRequest request) throws ResourceNotFoundException {
        clientService.updateClient(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
