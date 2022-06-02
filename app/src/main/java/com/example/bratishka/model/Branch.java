package com.example.bratishka.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class Branch implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("street")
    private String street;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("number_phone")
    private String number;
    @SerializedName("working_mode")
    private String working;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getWorking() {
        return working;
    }

    public void setWorking(String working) {
        this.working = working;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return id.equals(branch.id) && street.equals(branch.street)
                && latitude.equals(branch.latitude)
                && longitude.equals(branch.longitude)
                && number.equals(branch.number)
                && working.equals(branch.working);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, latitude, longitude, number, working);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id='" + id + '\'' +
                ", street='" + street + '\'' +
                ", latitide='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", number='" + number + '\'' +
                ", working='" + working + '\'' +
                '}';
    }
}
