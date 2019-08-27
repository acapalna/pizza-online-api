package org.fasttrackit.pizzaonlineapi.service;

import org.fasttrackit.pizzaonlineapi.domain.Cart;
import org.fasttrackit.pizzaonlineapi.domain.Client;
import org.fasttrackit.pizzaonlineapi.domain.Order;
import org.fasttrackit.pizzaonlineapi.domain.Pizza;
import org.fasttrackit.pizzaonlineapi.exception.ResourceNotFoundException;
import org.fasttrackit.pizzaonlineapi.repository.CartRepository;
import org.fasttrackit.pizzaonlineapi.repository.OrderRepository;
import org.fasttrackit.pizzaonlineapi.transfer.cart.AddPizzaToCartRequest;
import org.fasttrackit.pizzaonlineapi.transfer.cart.CartResponse;
import org.fasttrackit.pizzaonlineapi.transfer.client.ClientResponse;
import org.fasttrackit.pizzaonlineapi.transfer.order.AddPizzaToOrderRequest;
import org.fasttrackit.pizzaonlineapi.transfer.pizza.PizzaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ClientService clientService;
    private final PizzaService pizzaService;

    public OrderService(OrderRepository orderRepository, CartService cartService, ClientService clientService, PizzaService pizzaService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.clientService = clientService;
        this.pizzaService = pizzaService;
    }

    @Transactional
    public void addOrder(Long cartId) throws ResourceNotFoundException {
        CartResponse cartResponse = cartService.getCart(cartId);
        ClientResponse clientResponse = cartResponse.getClient();
        Set<PizzaResponse> pizzaResponse = cartResponse.getPizza();

        for(PizzaResponse pizza : pizzaResponse){
            addPizzaToOrder()
        }
    }

    @Transactional
    public void addPizzaToOrder(AddPizzaToOrderRequest request) throws ResourceNotFoundException {
        LOGGER.info("Saving cart {}", request);

        Client client = clientService.getClient(request.getClientId());
        Order order = orderRepository.findById(request.getClientId())
                .orElse(new Order());
        if(order.getClient() == null)
            order.setClient(client);

        Pizza pizza = pizzaService.getPizza(request.getPizzaId());
        order.addPizza(pizza);

        cartRepository.save(cart);
    }


}
