package com.example.bratishka.model;

import java.util.Objects;

public class Discount {
    private String type;
    private String text;
    private int idResource;

    public Discount(String type, String text, int idResource){
        this.type = type;
        this.text = text;
        this.idResource = idResource;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        Discount discount = (Discount) o;
        return idResource == discount.idResource && type.equals(discount.type) && text.equals(discount.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, text, idResource);
    }

    @Override
    public String toString() {
        return "Discount{" +
                "type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", idResource=" + idResource +
                '}';
    }
}
