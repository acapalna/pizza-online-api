package org.fasttrackit.pizzaonlineapi.transfer.cart;

import org.fasttrackit.pizzaonlineapi.transfer.client.ClientResponse;
import org.fasttrackit.pizzaonlineapi.transfer.pizza.PizzaResponse;

import java.util.HashSet;
import java.util.Set;

public class CartResponse {

    //DTO class Data transfer object

    private Long id;
    private ClientResponse client;
    private Set<PizzaResponse> pizza = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientResponse getClient() {
        return client;
    }

    public void setClient(ClientResponse client) {
        this.client = client;
    }

    public Set<PizzaResponse> getPizza() {
        return pizza;
    }

    public void setPizza(Set<PizzaResponse> pizza) {
        this.pizza = pizza;
    }

    @Override
    public String toString() {
        return "CartResponse{" +
                "id=" + id +
                ", client=" + client +
                ", pizza=" + pizza +
                '}';
    }
}
