package com.example.bratishka.model;

import java.io.Serializable;
import java.util.Objects;

public class Barber implements Serializable {
    private String nameSurname;
    private String position;
    private int idResource;

    public Barber(String nameSurname, String position, int idResource){
        this.nameSurname = nameSurname;
        this.position = position;
        this.idResource = idResource;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
        Barber barber = (Barber) o;
        return idResource == barber.idResource && nameSurname.equals(barber.nameSurname) && position.equals(barber.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameSurname, position, idResource);
    }

    @Override
    public String toString() {
        return "Barber{" +
                "name_surname='" + nameSurname + '\'' +
                ", position='" + position + '\'' +
                ", idResource=" + idResource +
                '}';
    }

}
