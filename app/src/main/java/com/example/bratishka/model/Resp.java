package com.example.bratishka.model;

import java.io.Serializable;
import java.util.Objects;

public class Resp implements Serializable {
    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resp resp = (Resp) o;
        return status.equals(resp.status) && message.equals(resp.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message);
    }

    @Override
    public String toString() {
        return "Resp{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
