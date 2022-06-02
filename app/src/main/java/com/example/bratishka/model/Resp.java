package com.example.bratishka.model;

import java.util.Objects;

public class Resp {
    private String status;
    private long id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resp resp = (Resp) o;
        return id == resp.id && status.equals(resp.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, id);
    }

    @Override
    public String toString() {
        return "Resp{" +
                "status='" + status + '\'' +
                ", id=" + id +
                '}';
    }

}
