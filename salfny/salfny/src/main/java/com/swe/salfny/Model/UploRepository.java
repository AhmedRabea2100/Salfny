package com.swe.salfny.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UploRepository extends JpaRepository<Uplo, Integer> {
    @Query("SELECT u.id FROM User u WHERE u.email = ?1")
    public String findByEmail(String email);

    @Query("SELECT MAX(id) FROM Uplo")
    public Long findMaxId();
}
