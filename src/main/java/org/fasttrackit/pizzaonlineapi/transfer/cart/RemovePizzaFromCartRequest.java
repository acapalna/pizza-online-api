package org.fasttrackit.pizzaonlineapi.transfer.cart;

public class RemovePizzaFromCartRequest {

    private Long clientId;
    private Long pizzaId;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Long pizzaId) {
        this.pizzaId = pizzaId;
    }

    @Override
    public String toString() {
        return "RemovePizzaFromCartRequest{" +
                "clientId=" + clientId +
                ", pizzaId=" + pizzaId +
                '}';
    }
}
