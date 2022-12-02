package com.swe.salfny.User;

import com.swe.salfny.post.Post;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "user")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "email",nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "profile_pic", length = 200)
    private String profilePic;

    @Column(name = "member_since", length = 200)
    private LocalDateTime memberSince;

    public LocalDateTime getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(LocalDateTime memberSince) {
        this.memberSince = memberSince;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Column(name = "password",nullable = false, length = 64)
    private String password;


    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "rating")
    private float rating;

    @Column(name = "no_of_done_deals")
    private int noOfDoneDeals;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public int getNoOfDoneDeals() {
        return noOfDoneDeals;
    }

    public void setNoOfDoneDeals(int noOfDoneDeals) {
        this.noOfDoneDeals = noOfDoneDeals;
    }

    @OneToMany(mappedBy="user")
    private Set<Post> posts;
}
