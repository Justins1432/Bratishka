package com.example.bratishka.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class Haircut implements Serializable {
   @SerializedName("id")
    private String id;
   @SerializedName("name")
    private String name;
   @SerializedName("price")
    private String price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
        Haircut haircut = (Haircut) o;
        return id.equals(haircut.id) && name.equals(haircut.name) && price.equals(haircut.price) && icon.equals(haircut.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, icon);
    }

    @Override
    public String toString() {
        return "Haircut{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
