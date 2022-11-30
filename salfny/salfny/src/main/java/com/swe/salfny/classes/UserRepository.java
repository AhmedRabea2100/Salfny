package com.swe.salfny.classes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserData, Long> {
    @Query("SELECT u FROM UserData u WHERE u.email = ?1")
    public UserData findByEmail(String email);
}