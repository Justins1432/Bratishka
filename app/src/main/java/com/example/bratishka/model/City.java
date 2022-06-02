package com.example.bratishka.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class City implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("city")
    private String name;
    @SerializedName("count_branches")
    private String count;

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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id.equals(city.id) && name.equals(city.name) && count.equals(city.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, count);
    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
