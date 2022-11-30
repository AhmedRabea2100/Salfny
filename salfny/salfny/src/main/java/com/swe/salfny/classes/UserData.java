package com.swe.salfny.classes;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email",nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "password",nullable = false, length = 64)
    private String password;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "phone_number", nullable = false, length = 11)
    private String phoneNumber;

    public void setEmail(String s) {this.email = s;}

    public void setPassword(String s) {
        this.password = s;
    }

    public void setFirstName(String s) {
        this.firstName = s;
    }

    public void setLastName(String s) {
        this.lastName = s;
    }

    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
