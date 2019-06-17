package org.fasttrackit.pizzaonlineapi.transfer.pizza;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreatePizzaRequest {

    @NotNull
    private String name;
    @NotNull
    @Min(0)
    private Double price;
    @Min(0)
    private Double salePrice;
    @NotNull
    @Min(0)
    private Double weight;
    private String ingredients;
    private String description;
    private String imagePath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "CreatePizzaRequest{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", salePrice=" + salePrice +
                ", weight=" + weight +
                ", ingredients='" + ingredients + '\'' +
                ", description='" + description + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
