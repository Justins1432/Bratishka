package com.example.bratishka.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

public class Schedule implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("monday_start")
    private String monday_start;
    @SerializedName("monday_end")
    private String monday_end;
    @SerializedName("tuesday_start")
    private String tuesday_start;
    @SerializedName("tuesday_end")
    private String tuesday_end;
    @SerializedName("wednesday_start")
    private String wednesday_start;
    @SerializedName("wednesday_end")
    private String wednesday_end;
    @SerializedName("thursday_start")
    private String thursday_start;
    @SerializedName("thursday_end")
    private String thursday_end;
    @SerializedName("friday_start")
    private String friday_start;
    @SerializedName("friday_end")
    private String friday_end;
    @SerializedName("saturday_start")
    private String saturday_start;
    @SerializedName("saturday_end")
    private String saturday_end;
    @SerializedName("sunday_start")
    private String sunday_start;
    @SerializedName("sunday_end")
    private String sunday_end;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonday_start() {
        return monday_start;
    }

    public void setMonday_start(String monday_start) {
        this.monday_start = monday_start;
    }

    public String getMonday_end() {
        return monday_end;
    }

    public void setMonday_end(String monday_end) {
        this.monday_end = monday_end;
    }

    public String getTuesday_start() {
        return tuesday_start;
    }

    public void setTuesday_start(String tuesday_start) {
        this.tuesday_start = tuesday_start;
    }

    public String getTuesday_end() {
        return tuesday_end;
    }

    public void setTuesday_end(String tuesday_end) {
        this.tuesday_end = tuesday_end;
    }

    public String getWednesday_start() {
        return wednesday_start;
    }

    public void setWednesday_start(String wednesday_start) {
        this.wednesday_start = wednesday_start;
    }

    public String getWednesday_end() {
        return wednesday_end;
    }

    public void setWednesday_end(String wednesday_end) {
        this.wednesday_end = wednesday_end;
    }

    public String getThursday_start() {
        return thursday_start;
    }

    public void setThursday_start(String thursday_start) {
        this.thursday_start = thursday_start;
    }

    public String getThursday_end() {
        return thursday_end;
    }

    public void setThursday_end(String thursday_end) {
        this.thursday_end = thursday_end;
    }

    public String getFriday_start() {
        return friday_start;
    }

    public void setFriday_start(String friday_start) {
        this.friday_start = friday_start;
    }

    public String getFriday_end() {
        return friday_end;
    }

    public void setFriday_end(String friday_end) {
        this.friday_end = friday_end;
    }

    public String getSaturday_start() {
        return saturday_start;
    }

    public void setSaturday_start(String saturday_start) {
        this.saturday_start = saturday_start;
    }

    public String getSaturday_end() {
        return saturday_end;
    }

    public void setSaturday_end(String saturday_end) {
        this.saturday_end = saturday_end;
    }

    public String getSunday_start() {
        return sunday_start;
    }

    public void setSunday_start(String sunday_start) {
        this.sunday_start = sunday_start;
    }

    public String getSunday_end() {
        return sunday_end;
    }

    public void setSunday_end(String sunday_end) {
        this.sunday_end = sunday_end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return id.equals(schedule.id) && monday_start.equals(schedule.monday_start) && monday_end.equals(schedule.monday_end) && tuesday_start.equals(schedule.tuesday_start) && tuesday_end.equals(schedule.tuesday_end) && wednesday_start.equals(schedule.wednesday_start) && wednesday_end.equals(schedule.wednesday_end) && thursday_start.equals(schedule.thursday_start) && thursday_end.equals(schedule.thursday_end) && friday_start.equals(schedule.friday_start) && friday_end.equals(schedule.friday_end) && saturday_start.equals(schedule.saturday_start) && saturday_end.equals(schedule.saturday_end) && sunday_start.equals(schedule.sunday_start) && sunday_end.equals(schedule.sunday_end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, monday_start, monday_end, tuesday_start, tuesday_end, wednesday_start, wednesday_end, thursday_start, thursday_end, friday_start, friday_end, saturday_start, saturday_end, sunday_start, sunday_end);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id='" + id + '\'' +
                ", monday_start='" + monday_start + '\'' +
                ", monday_end='" + monday_end + '\'' +
                ", tuesday_start='" + tuesday_start + '\'' +
                ", tuesday_end='" + tuesday_end + '\'' +
                ", wednesday_start='" + wednesday_start + '\'' +
                ", wednesday_end='" + wednesday_end + '\'' +
                ", thursday_start='" + thursday_start + '\'' +
                ", thursday_end='" + thursday_end + '\'' +
                ", friday_start='" + friday_start + '\'' +
                ", friday_end='" + friday_end + '\'' +
                ", saturday_start='" + saturday_start + '\'' +
                ", saturday_end='" + saturday_end + '\'' +
                ", sunday_start='" + sunday_start + '\'' +
                ", sunday_end='" + sunday_end + '\'' +
                '}';
    }
}
