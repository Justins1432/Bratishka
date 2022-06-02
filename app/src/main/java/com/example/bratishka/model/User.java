package com.example.bratishka.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("surname")
    private String surname;
    @SerializedName("name")
    private String name;
    @SerializedName("fathername")
    private String fathername;
    @SerializedName("email")
    private String email;
    @SerializedName("date_birth")
    private String dateBirth;
    @SerializedName("city")
    private String city;
    @SerializedName("number_phone")
    private String numberPhone;
    @SerializedName("password")
    private String password;
    @SerializedName("code")
    private String code;
    @SerializedName("image")
    private String image;
    @SerializedName("basket_id")
    private String idBasket;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIdBasket() {
        return idBasket;
    }

    public void setIdBasket(String idBasket) {
        this.idBasket = idBasket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id)
                && surname.equals(user.surname)
                && name.equals(user.name)
                && fathername.equals(user.fathername)
                && email.equals(user.email)
                && dateBirth.equals(user.dateBirth)
                && city.equals(user.city)
                && numberPhone.equals(user.numberPhone)
                && password.equals(user.password)
                && code.equals(user.code)
                && image.equals(user.image)
                && idBasket.equals(user.idBasket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, fathername, email, dateBirth, city, numberPhone, password, code, image, idBasket);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", fathername='" + fathername + '\'' +
                ", email='" + email + '\'' +
                ", dateBirth='" + dateBirth + '\'' +
                ", city='" + city + '\'' +
                ", numberPhone='" + numberPhone + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                ", image='" + image + '\'' +
                ", idBasket='" + idBasket + '\'' +
                '}';
    }
}
