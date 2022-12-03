package com.swe.salfny.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserData, Integer> {
    @Query("SELECT u.id FROM UserData u WHERE u.email = ?1")
    public String findByEmail(String email);

    @Query("SELECT u.password FROM UserData u WHERE u.email = ?1")
    public String authenticate(String email);
}