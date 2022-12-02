package com.swe.salfny.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserData, Integer> {
    @Query("SELECT u FROM UserData u WHERE u.email = ?1")
    public UserData findByEmail(String email);

    @Query("SELECT u.password FROM UserData u WHERE u.email = ?1")
    public String authenticate(String email);
}