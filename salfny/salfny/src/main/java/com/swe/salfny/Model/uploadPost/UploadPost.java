package com.swe.salfny.Model.uploadPost;

import com.swe.salfny.Model.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class    UploadPost {
    public UploadPost() {
    }

    @Override
    public String toString() {
        return "UploadPost{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category_id=" + category_id +
                ", user_id=" + user_id +
                ", photo='" + photo + '\'' +
                '}';
    }

    String title;
    String description;
    int price;
    int category_id;
    int user_id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    String photo;

    public UploadPost(String title, String description, int price, int category_id, int user_id, String photo) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.category_id = category_id;
        this.user_id = user_id;
        this.photo = photo;
    }


}
