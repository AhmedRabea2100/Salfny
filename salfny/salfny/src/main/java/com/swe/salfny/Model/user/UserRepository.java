package com.swe.salfny.Model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u.id FROM User u WHERE u.email = ?1")
    public String findByEmail(String email);

    @Query("SELECT u.id,u.password FROM User u WHERE u.email = ?1")
    public String authenticate(String email);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User getProfile(String email);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO view (user_id,post_id) VALUES (?1,?2)", nativeQuery = true)
    public void addView(int userid, int postid);
}