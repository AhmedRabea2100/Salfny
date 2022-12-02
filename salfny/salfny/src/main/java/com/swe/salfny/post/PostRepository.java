package com.swe.salfny.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value = "SELECT * FROM post p ORDER BY p.date LIMIT 3", nativeQuery = true)
    public List<Post> showRecentPosts();
}
