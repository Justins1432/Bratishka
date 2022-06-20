package com.example.bratishka.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Record implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("date_record")
    private String date;
    @SerializedName("user_email")
    private String email;
    @SerializedName("h_name")
    private String name;
    @SerializedName("h_price")
    private String price;
    @SerializedName("schedule")
    private String schedule;
    @SerializedName("b_name")
    private String b_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dDate = dateFormat.parse(date);
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(dDate);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return id.equals(record.id)
                && date.equals(record.date)
                && email.equals(record.email)
                && name.equals(record.name)
                && price.equals(record.price)
                && schedule.equals(record.schedule)
                && b_name.equals(record.b_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, email, name, price, schedule, b_name);
    }

    @Override
    public String toString() {
        return "Record{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", schedule='" + schedule + '\'' +
                ", b_name='" + b_name + '\'' +
                '}';
    }
}
