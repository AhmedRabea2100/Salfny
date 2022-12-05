package com.swe.salfny.classes;

import java.util.List;

public class User {
    int id;
    String name;
    String email;
    String password;
    String address;
    type gender;
    float rating;
    List offeredProducts;
    List rentedProducts;
    enum type{
        male,
        female
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public type getGender() {
        return gender;
    }

    public void setGender(type gender) {
        this.gender = gender;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List getOfferedProducts() {
        return offeredProducts;
    }

    public void setOfferedProducts(List offeredProducts) {
        this.offeredProducts = offeredProducts;
    }

    public List getRentedProducts() {
        return rentedProducts;
    }

    public void setRentedProducts(List rentedProducts) {
        this.rentedProducts = rentedProducts;
    }


}
