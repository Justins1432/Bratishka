package com.example.bratishka.model;

import java.util.Objects;

public class Product {
    private String type;
    private String price;
    private String description;
    private double rating;
    private int idResource;

    public Product(String type, String price, String description, double rating, int idResource){
        this.type = type;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.idResource = idResource;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getIdResource() {
        return idResource;
    }

    public void setIdResource(int idResource) {
        this.idResource = idResource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.rating, rating) == 0
                && idResource == product.idResource
                && type.equals(product.type)
                && price.equals(product.price)
                && description.equals(product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, price, description, rating, idResource);
    }

    @Override
    public String toString() {
        return "Product{" +
                "type='" + type + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", idResource=" + idResource +
                '}';
    }
}
