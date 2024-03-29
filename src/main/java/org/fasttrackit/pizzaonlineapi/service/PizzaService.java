package org.fasttrackit.pizzaonlineapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.pizzaonlineapi.domain.Pizza;
import org.fasttrackit.pizzaonlineapi.exception.ResourceNotFoundException;
import org.fasttrackit.pizzaonlineapi.repository.PizzaRepository;
import org.fasttrackit.pizzaonlineapi.transfer.pizza.CreatePizzaRequest;
import org.fasttrackit.pizzaonlineapi.transfer.pizza.GetPizzaRequest;
import org.fasttrackit.pizzaonlineapi.transfer.pizza.PizzaResponse;
import org.fasttrackit.pizzaonlineapi.transfer.pizza.UpdatePizzaRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PizzaService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(PizzaService.class);

    //@Autowired //IoC (inversion of control) and dependency injection
    private final PizzaRepository pizzaRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, ObjectMapper objectMapper) {
        this.pizzaRepository = pizzaRepository;
        this.objectMapper = objectMapper;
    }

    public Pizza createPizza(CreatePizzaRequest request){
        LOGGER.info("Creating pizza {}", request);

        Pizza pizza = objectMapper.convertValue(request, Pizza.class);

        return pizzaRepository.save(pizza);
    }

    public Pizza updatePizza(long id, UpdatePizzaRequest request) throws ResourceNotFoundException {
        LOGGER.info("Updating pizza {} with {}", id, request);

        Pizza pizza = getPizza(id);
        BeanUtils.copyProperties(request, pizza);

        return pizzaRepository.save(pizza);
    }

    public void deletePizza(long id){
        LOGGER.info("Deleting pizza {}", id);
        pizzaRepository.deleteById(id);
        LOGGER.info("Deleted pizza {}", id);
    }


    public Pizza getPizza(Long id) throws ResourceNotFoundException {
        LOGGER.info("Retrieving pizza {}", id);
        //using optional orElseTrow

        return pizzaRepository.findById(id)
                //using Lambda expressions
                .orElseThrow(() -> new ResourceNotFoundException("Pizza " + id + " does not exist"));
    }

    public PizzaResponse getPizzaById(Long id) throws ResourceNotFoundException {
        LOGGER.info("Retrieving pizza {}", id);

        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pizza " + id + " does not exist"));

        return pizzaToPizzaResponseConverter(pizza);
    }

    public Page<PizzaResponse> getPizza(GetPizzaRequest request, Pageable pageable) {
        LOGGER.info("Retrieving pizza {}", request);

        Page<Pizza> pizzas;

        if (request.getPartialName() != null && request.getMinWeight() != null) {
            pizzas = pizzaRepository.findByNameContainingAndWeightGreaterThanEqual(request.getPartialName(), request.getMinWeight(), pageable);
        }
        else if (request.getPartialName() != null) {
            pizzas = pizzaRepository.findByNameContaining(request.getPartialName(), pageable);
        }
        else if (request.getPartialName() != null && request.getMinPrice() != null && request.getMaxPrice() != null) {
            pizzas = pizzaRepository.findByNameContainingAndPriceBetween(request.getPartialName(), request.getMinPrice(), request.getMaxPrice(), pageable);
        }
        else if (request.getMinPrice() != null && request.getMaxPrice() != null) {
            pizzas = pizzaRepository.findAllByPriceBetween(request.getMinPrice(), request.getMaxPrice(), pageable);
        }
        else if (request.getMinPrice() != null && request.getMaxPrice() == null) {
            pizzas = pizzaRepository.findAllByPriceGreaterThan(request.getMinPrice(), pageable);
        }
        else if (request.getMaxPrice() != null && request.getMinPrice() == null) {
            pizzas = pizzaRepository.findAllByPriceLessThan(request.getMaxPrice(), pageable);
        }else {
            pizzas = pizzaRepository.findAll(pageable);
        }

        List<PizzaResponse> pizzaResponses = new ArrayList<>();

        pizzas.getContent().forEach(pizza -> {
            PizzaResponse pizzaResponse = pizzaToPizzaResponseConverter(pizza);
            pizzaResponses.add(pizzaResponse);
        });

        return new PageImpl<>(pizzaResponses, pageable, pizzas.getTotalElements());
    }

    public PizzaResponse pizzaToPizzaResponseConverter(Pizza pizza){
        PizzaResponse pizzaResponse = new PizzaResponse();
        pizzaResponse.setId(pizza.getId());
        pizzaResponse.setName(pizza.getName());
        pizzaResponse.setPrice(pizza.getPrice());
        pizzaResponse.setSalePrice(pizza.getSalePrice());
        pizzaResponse.setWeight(pizza.getWeight());
        pizzaResponse.setIngredients(pizza.getIngredients());
        pizzaResponse.setDescription(pizza.getDescription());
        pizzaResponse.setImagePath(pizza.getImagePath());

        return pizzaResponse;
    }

}
