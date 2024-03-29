package org.fasttrackit.pizzaonlineapi.web;

import org.fasttrackit.pizzaonlineapi.domain.Pizza;
import org.fasttrackit.pizzaonlineapi.exception.ResourceNotFoundException;
import org.fasttrackit.pizzaonlineapi.service.PizzaService;
import org.fasttrackit.pizzaonlineapi.transfer.pizza.CreatePizzaRequest;
import org.fasttrackit.pizzaonlineapi.transfer.pizza.GetPizzaRequest;
import org.fasttrackit.pizzaonlineapi.transfer.pizza.PizzaResponse;
import org.fasttrackit.pizzaonlineapi.transfer.pizza.UpdatePizzaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    //endpoint
    @GetMapping
    public ResponseEntity<Page<PizzaResponse>> getPizzas(GetPizzaRequest request, Pageable pageable){
        Page<PizzaResponse> response = pizzaService.getPizza(request, pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pizza> createPizza(@RequestBody @Valid CreatePizzaRequest request){
        Pizza pizza = pizzaService.createPizza(request);
        return new ResponseEntity<>(pizza, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePizza(@PathVariable Long id){
        pizzaService.deletePizza(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Pizza> getPizza(@PathVariable("id") Long id) throws ResourceNotFoundException {
//        Pizza pizza = pizzaService.getPizza(id);
//        return new ResponseEntity<>(pizza, HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaResponse> getPizza(@PathVariable("id") Long id) throws ResourceNotFoundException {
        PizzaResponse response = pizzaService.getPizzaById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePizza(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdatePizzaRequest request) throws ResourceNotFoundException {
        pizzaService.updatePizza(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
