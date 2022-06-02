package com.example.bratishka.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class Message implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("name_msg")
    private String type;
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id.equals(message.id) && type.equals(message.type) && name.equals(message.name) && image.equals(message.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, image);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
