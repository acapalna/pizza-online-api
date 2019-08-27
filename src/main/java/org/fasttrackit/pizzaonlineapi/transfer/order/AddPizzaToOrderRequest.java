package org.fasttrackit.pizzaonlineapi.transfer.order;

public class AddPizzaToOrderRequest {

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
        return "AddPizzaToOrderRequest{" +
                "clientId=" + clientId +
                ", pizzaId=" + pizzaId +
                '}';
    }
}
