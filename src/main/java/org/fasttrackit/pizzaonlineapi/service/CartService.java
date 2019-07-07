package org.fasttrackit.pizzaonlineapi.service;

import org.fasttrackit.pizzaonlineapi.domain.Cart;
import org.fasttrackit.pizzaonlineapi.domain.Client;
import org.fasttrackit.pizzaonlineapi.exception.ResourceNotFoundException;
import org.fasttrackit.pizzaonlineapi.repository.CartRepository;
import org.fasttrackit.pizzaonlineapi.transfer.cart.AddPizzaToCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);
    private final CartRepository cartRepository;
    private final ClientService clientService;

    public CartService(CartRepository cartRepository, ClientService clientService) {
        this.cartRepository = cartRepository;
        this.clientService = clientService;
    }

    @Transactional
    public void addPizzaToCart(AddPizzaToCart request) throws ResourceNotFoundException {
        LOGGER.info("Saving cart {}", request);

        Client client = clientService.getClient(request.getClientId());
        Cart cart = new Cart();
        cart.setClient(client);

        cartRepository.save(cart);
    }
}
