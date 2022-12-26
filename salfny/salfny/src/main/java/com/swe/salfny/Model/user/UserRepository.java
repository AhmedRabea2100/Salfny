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

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    public User getUser(int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT IGNORE INTO view (user_id,post_id) VALUES (?1,?2)", nativeQuery = true)
    public void addView(int userid, int postid);


    @Query(value="SELECT * FROM save WHERE user_id = ?1 AND post_id= ?2", nativeQuery = true)
    public Object[] checkExists(int user_id, int post_id);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO save (user_id,post_id) VALUES (?1,?2)", nativeQuery = true)
    public void addSave(int userid, int postid);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM save WHERE user_id= ?1 AND post_id= ?2", nativeQuery = true)
    public void deleteSave(int userid, int postid);
}