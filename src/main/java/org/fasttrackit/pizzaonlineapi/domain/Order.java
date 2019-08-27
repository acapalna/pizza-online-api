package org.fasttrackit.pizzaonlineapi.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Order {

    @Id
    private long id;

    @OneToMany(fetch = FetchType.EAGER)
    @MapsId
    private Client client;

    private Pizza pizza;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "order_pizza",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    private Set<Pizza> orderPizzas = new HashSet<>();

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

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
}
