package com.swe.salfny.Model;

import com.swe.salfny.Model.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "Uplo")
public class Uplo {

    public Uplo() {}

    public Uplo(String title, String description, int price,String category,String photo){
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.photo=photo;
        //this.user_id = user_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "price", nullable = false)
    private int price;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "category", nullable = false)
    private String category;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Column(name = "photo", nullable = false)
    private String photo;


    public int getId() {
        return id;
    }

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





}