package org.fasttrackit.pizzaonlineapi.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart {

    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Client client;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "cart_pizza",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    private Set<Pizza> pizzas = new HashSet<>();

    public void addPizza(Pizza pizza){
        // adding pizza to current cart
        pizzas.add(pizza);
        // adding current cart to the carts collection of the received pizzas
        pizza.getCarts().add(this);
    }

    public void removePizza(Pizza pizza){
        pizzas.remove(pizza);
        pizza.getCarts().remove(this);
    }

    public Set<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(Set<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


}
