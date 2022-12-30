package com.swe.salfny.Model.photo;

import com.swe.salfny.Model.photo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    @Query("SELECT MAX(id) FROM Photo")
    public int findMaxId();
}
