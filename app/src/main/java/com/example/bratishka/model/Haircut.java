package com.example.bratishka.model;

import java.util.Objects;

public class Haircut {
    private String price;
    private String name;
    private int idResource;

    public Haircut(String price, String name, int idResource){
        this.price = price;
        this.name = name;
        this.idResource = idResource;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Haircut haircut = (Haircut) o;
        return idResource == haircut.idResource && price.equals(haircut.price) && name.equals(haircut.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, name, idResource);
    }

    @Override
    public String toString() {
        return "Haircut{" +
                "price='" + price + '\'' +
                ", name='" + name + '\'' +
                ", idResource=" + idResource +
                '}';
    }
}
