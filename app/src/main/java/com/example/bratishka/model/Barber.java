package com.example.bratishka.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class Barber implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("position")
    private String position;
    @SerializedName("name")
    private String name;
    @SerializedName("rating")
    private String rating;
    @SerializedName("image")
    private String image;
    @SerializedName("branch_id")
    private String branch_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(String branch_id) {
        this.branch_id = branch_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barber barber = (Barber) o;
        return id.equals(barber.id)
                && position.equals(barber.position)
                && name.equals(barber.name)
                && rating.equals(barber.rating)
                && image.equals(barber.image)
                && branch_id.equals(barber.branch_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, position, name, rating, image, branch_id);
    }

    @Override
    public String toString() {
        return "Barber{" +
                "id='" + id + '\'' +
                ", position='" + position + '\'' +
                ", name='" + name + '\'' +
                ", rating='" + rating + '\'' +
                ", image='" + image + '\'' +
                ", branch_id='" + branch_id + '\'' +
                '}';
    }
}
