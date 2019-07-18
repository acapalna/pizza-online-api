package org.fasttrackit.pizzaonlineapi.transfer.pizza;

public class PizzaResponse {

    private long id;
    private String name;
    private Double price;
    private Double salePrice;
    private Double weight;
    private String ingredients;
    private String description;
    private String imagePath;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
        return "PizzaResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", salePrice=" + salePrice +
                ", weight=" + weight +
                ", ingredients='" + ingredients + '\'' +
                ", description='" + description + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
