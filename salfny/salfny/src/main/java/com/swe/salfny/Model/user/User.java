package com.swe.salfny.Model.user;

import com.swe.salfny.Model.post.Post;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "user")
public class User {

    public User() {}

    public User(String username, String address, String email, String profilePic, LocalDateTime memberSince, String password, String phoneNumber, float rating, int noOfDoneDeals) {
        this.username = username;
        this.address = address;
        this.email = email;
        this.profilePic = profilePic;
        this.memberSince = memberSince;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
        this.noOfDoneDeals = noOfDoneDeals;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "profile_pic", length = 200)
    private String profilePic;

    @Column(name = "member_since", nullable = false, length = 200)
    private LocalDateTime memberSince;

    @Column(name = "password", nullable = false, length = 64)
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void hashPassword(int LOG_ROUNDS) {
        password = BCrypt.hashpw(password, BCrypt.gensalt(LOG_ROUNDS));
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

    public void setRating(float rating) {
        this.rating = rating;
    }

    public LocalDateTime getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(LocalDateTime memberSince) {
        this.memberSince = memberSince;
    }

    public int getNoOfDoneDeals() {
        return noOfDoneDeals;
    }

    public void setNoOfDoneDeals(int noOfDoneDeals) {
        this.noOfDoneDeals = noOfDoneDeals;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Post> posts;

}
