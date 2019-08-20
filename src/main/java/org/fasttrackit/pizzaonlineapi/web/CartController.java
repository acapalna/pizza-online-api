package org.fasttrackit.pizzaonlineapi.web;

import org.fasttrackit.pizzaonlineapi.exception.ResourceNotFoundException;
import org.fasttrackit.pizzaonlineapi.service.CartService;
import org.fasttrackit.pizzaonlineapi.transfer.cart.AddPizzaToCartRequest;
import org.fasttrackit.pizzaonlineapi.transfer.cart.CartResponse;
import org.fasttrackit.pizzaonlineapi.transfer.cart.RemovePizzaFromCartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    //spring boot AOP
    @PutMapping
    public ResponseEntity addPizzaToCart(@RequestBody @Valid AddPizzaToCartRequest request) throws ResourceNotFoundException {
        cartService.addPizzaToCart(request);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<CartResponse> getCart(@PathVariable("clientId") Long clientId) throws ResourceNotFoundException {
        CartResponse cart = cartService.getCart(clientId);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity removePizzaFromCart(Long clientId, Long pizzaId) throws ResourceNotFoundException {
        RemovePizzaFromCartRequest request = new RemovePizzaFromCartRequest();
        request.setClientId(clientId);
        request.setPizzaId(pizzaId);

        cartService.removePizzaFromCart(request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
