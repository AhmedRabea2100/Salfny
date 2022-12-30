package com.swe.salfny.Model.preferences;

import com.swe.salfny.Model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PreferencesRepository extends JpaRepository<Preferences, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO preferences VALUES (?1, ?2)", nativeQuery = true)
    void insert(int user_id, int category_id);
}
