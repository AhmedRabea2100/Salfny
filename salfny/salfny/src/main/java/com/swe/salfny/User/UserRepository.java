package com.swe.salfny.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserData, Long> {
    @Query("SELECT u FROM UserData u WHERE u.email = ?1")
    public UserData findByEmail(String email);

    @Query("SELECT u.password FROM UserData u WHERE u.email = ?1")
    public List<Object[]> authenticate(String email);
}