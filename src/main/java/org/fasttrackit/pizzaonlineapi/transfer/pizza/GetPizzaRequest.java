package org.fasttrackit.pizzaonlineapi.transfer.pizza;

public class GetPizzaRequest {

    private String partialName;
    private Double minWeight;
    private Double maxWeight;
    private Double minPrice;
    private Double maxPrice;
    private String ingredients;

    public String getPartialName() {
        return partialName;
    }

    public void setPartialName(String partialName) {
        this.partialName = partialName;
    }

    public Double getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(Double minWeight) {
        this.minWeight = minWeight;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "GetPizzaRequest{" +
                "partialName='" + partialName + '\'' +
                ", minWeight=" + minWeight +
                ", maxWeight=" + maxWeight +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", ingredients='" + ingredients + '\'' +
                '}';
    }
}
