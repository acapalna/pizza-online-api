package org.fasttrackit.pizzaonlineapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.pizzaonlineapi.domain.Client;
import org.fasttrackit.pizzaonlineapi.exception.ResourceNotFoundException;
import org.fasttrackit.pizzaonlineapi.repository.ClientRepository;
import org.fasttrackit.pizzaonlineapi.transfer.client.CreateClientRequest;
import org.fasttrackit.pizzaonlineapi.transfer.client.GetClientRequest;
import org.fasttrackit.pizzaonlineapi.transfer.client.UpdateClientRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ClientService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ClientService.class);

    //@Autowired //IoC (inversion of control) and dependency injection
    private final ClientRepository clientRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, ObjectMapper objectMapper) {
        this.clientRepository = clientRepository;
        this.objectMapper = objectMapper;
    }

    public Client createClient(CreateClientRequest request){
        LOGGER.info("Creating client {}", request);

        Client client = objectMapper.convertValue(request, Client.class);


        return clientRepository.save(client);
    }

    public Client getClient(long id) throws ResourceNotFoundException {
        LOGGER.info("Retrieving client {}", id);
        //using optional orElseTrow
        return clientRepository.findById(id)
                //using Lambda expressions
                .orElseThrow(() -> new ResourceNotFoundException("Client " + id + " does not exist"));
    }

    public Client updateClient(long id, UpdateClientRequest request) throws ResourceNotFoundException {
        LOGGER.info("Updating client {} with {}", id, request);

        Client client = getClient(id);
        BeanUtils.copyProperties(request, client);

        return clientRepository.save(client);
    }

    public void deleteClient(long id){
        LOGGER.info("Deleting client {}", id);
        clientRepository.deleteById(id);
        LOGGER.info("Deleted client {}", id);
    }

    public Page<Client> getClient(GetClientRequest request, Pageable pageable) {
        LOGGER.info("Retrieving client {}", request);

        if(request.getPartialId() != null){
            return clientRepository.findByIdContaining(request.getPartialId(), pageable);
        }
        else if(request.getPartialFirstName() != null){
            return clientRepository.findByFirstNameContaining(request.getPartialFirstName(), pageable);
        }
        else if(request.getPartialLastName() != null){
            return clientRepository.findByLastNameContaining(request.getPartialLastName(), pageable);
        }
        else if(request.getPartialPhoneNumber() != null){
            return clientRepository.findByPhoneNumberContaining(request.getPartialPhoneNumber(), pageable);
        }

        return clientRepository.findAll(pageable);
    }

}
