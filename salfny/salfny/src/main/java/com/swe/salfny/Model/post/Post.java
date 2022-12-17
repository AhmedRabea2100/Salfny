package com.swe.salfny.Model.post;

import com.swe.salfny.Model.image.Photo;
import com.swe.salfny.Model.user.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {

    public Post() {}

    public Post(String title, String description, int price, Integer payment_option, int views, String date, int category_id, int user_id, String address) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.payment_option = payment_option;
        this.views = views;
        this.date = date;
        this.category_id = category_id;
        this.user_id = user_id;
        this.address=address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false, length = 45)
    private String title;
    @Column(name = "address", nullable = false, length = 100)
    private String address;

    public String getAddress() {
        return address;
    }

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "payment_option")
    private Integer payment_option;

    @Column(name = "views")
    private int views;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "category_id", nullable = false)
    private int category_id;

    @Column(name = "user_id", nullable = false)
    private int user_id;

    @OneToMany(mappedBy = "postt", fetch = FetchType.LAZY)
    private Set<Photo> photos;

    public List<String> getPhotos() {
        List<String> urls = new ArrayList<>(photos.size());
        for(Photo photo:photos) urls.add(photo.getUrl());
        return urls;
    }

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

    public Integer getPayment_option() {
        return payment_option;
    }

    public void setPayment_option(Integer payment_option) {
        this.payment_option = payment_option;
    }

    public int getViews() {
        return views;
    }

    public void incrementViews() {
        this.views++;
    }

    public String getDate() {
        return date;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    public String getUserName() {
        return user.getUsername();
    }
}