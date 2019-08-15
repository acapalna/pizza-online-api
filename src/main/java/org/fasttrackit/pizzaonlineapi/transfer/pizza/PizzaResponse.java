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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PizzaResponse)) return false;

        PizzaResponse that = (PizzaResponse) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        if (!price.equals(that.price)) return false;
        if (salePrice != null ? !salePrice.equals(that.salePrice) : that.salePrice != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (ingredients != null ? !ingredients.equals(that.ingredients) : that.ingredients != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return imagePath != null ? imagePath.equals(that.imagePath) : that.imagePath == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + (salePrice != null ? salePrice.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }
}
