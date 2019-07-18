package org.fasttrackit.pizzaonlineapi.service;

import org.fasttrackit.pizzaonlineapi.domain.Cart;
import org.fasttrackit.pizzaonlineapi.domain.Client;
import org.fasttrackit.pizzaonlineapi.domain.Pizza;
import org.fasttrackit.pizzaonlineapi.exception.ResourceNotFoundException;
import org.fasttrackit.pizzaonlineapi.repository.CartRepository;
import org.fasttrackit.pizzaonlineapi.transfer.cart.AddPizzaToCartRequest;
import org.fasttrackit.pizzaonlineapi.transfer.cart.CartResponse;
import org.fasttrackit.pizzaonlineapi.transfer.cart.RemovePizzaFromCartRequest;
import org.fasttrackit.pizzaonlineapi.transfer.client.ClientResponse;
import org.fasttrackit.pizzaonlineapi.transfer.pizza.PizzaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);
    private final CartRepository cartRepository;
    private final ClientService clientService;
    private final PizzaService pizzaService;

    public CartService(CartRepository cartRepository, ClientService clientService, PizzaService pizzaService) {
        this.cartRepository = cartRepository;
        this.clientService = clientService;
        this.pizzaService = pizzaService;
    }

    @Transactional
    public void addPizzaToCart(AddPizzaToCartRequest request) throws ResourceNotFoundException {
        LOGGER.info("Saving cart {}", request);

        Client client = clientService.getClient(request.getClientId());
        Cart cart = cartRepository.findById(request.getClientId())
                .orElse(new Cart());
        if(cart.getClient() == null)
            cart.setClient(client);

        Pizza pizza = pizzaService.getPizza(request.getPizzaId());
        cart.addPizza(pizza);

        cartRepository.save(cart);
    }

    @Transactional
    public void removeProductFromCart(RemovePizzaFromCartRequest request) throws ResourceNotFoundException {
        LOGGER.info("Removing product from cart {}", request);

        Client client = clientService.getClient(request.getClientId());
        Cart cart = cartRepository.findById(request.getClientId())
                .orElse(new Cart());
        if(cart.getClient() == null)
            cart.setClient(client);

        Pizza pizza = pizzaService.getPizza(request.getPizzaId());
        cart.removePizza(pizza);

        cartRepository.save(cart);
    }

    @Transactional
    public CartResponse getCart(Long clientId) throws ResourceNotFoundException {
        Cart cart = cartRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cart " + clientId + " does not exist"));

        // using a DTO and reading all necessary properties (with getters)
        // to avoid LazyInitializationException when properties are loaded lazily
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setId(cart.getClient().getId());
        clientResponse.setFirstName(cart.getClient().getFirstName());
        clientResponse.setLastName(cart.getClient().getLastName());

        CartResponse cartResponse = new CartResponse();
        cartResponse.setId(cart.getId());
        cartResponse.setClient(clientResponse);

        cart.getPizzas().forEach(pizza -> {
            PizzaResponse pizzaResponse = new PizzaResponse();
            pizzaResponse.setId(pizza.getId());
            pizzaResponse.setName(pizza.getName());
            pizzaResponse.setPrice(pizza.getPrice());
            pizzaResponse.setSalePrice(pizza.getSalePrice());
            pizzaResponse.setWeight(pizza.getWeight());
            pizzaResponse.setIngredients(pizza.getIngredients());
            pizzaResponse.setDescription(pizza.getDescription());
            pizzaResponse.setImagePath(pizza.getImagePath());

            cartResponse.getPizza().add(pizzaResponse);
        });


        return cartResponse;
    }
}
