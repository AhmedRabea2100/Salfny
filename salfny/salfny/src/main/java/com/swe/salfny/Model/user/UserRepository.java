package com.swe.salfny.Model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u.id FROM User u WHERE u.email = ?1")
    public String findByEmail(String email);

    @Query("SELECT u.password FROM User u WHERE u.email = ?1")
    public String authenticate(String email);

}