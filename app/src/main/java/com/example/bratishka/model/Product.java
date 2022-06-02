package com.example.bratishka.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private String type;
    @SerializedName("price")
    private String price;
    @SerializedName("description")
    private String description;
    @SerializedName("rating")
    private String rating;
    @SerializedName("image")
    private String image;
    @SerializedName("icon")
    private String icon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id)
                && name.equals(product.name)
                && type.equals(product.type)
                && price.equals(product.price)
                && description.equals(product.description)
                && rating.equals(product.rating)
                && image.equals(product.image)
                && icon.equals(product.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, price, description, rating, image, icon);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", rating='" + rating + '\'' +
                ", image='" + image + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
