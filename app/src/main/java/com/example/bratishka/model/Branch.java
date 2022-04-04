package com.example.bratishka.model;

import java.util.Objects;

public class Branch {
    private String address;
    private double latitude;
    private double longitude;
    private int idResource;

    public Branch(){}

    public Branch(String address, double latitude, double longitude, int idResource) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.idResource = idResource;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
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
        Branch branch = (Branch) o;
        return Double.compare(branch.longitude, longitude) == 0 && Double.compare(branch.latitude, latitude) == 0
                && idResource == branch.idResource && address.equals(branch.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, longitude, latitude, idResource);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "address='" + address + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", idResource=" + idResource +
                '}';
    }
}
