package com.swe.salfny.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value = "SELECT * FROM post p ORDER BY p.date LIMIT 3", nativeQuery = true)
    public List<Post> showRecentPosts();

    @Query(value = "SELECT p.* FROM " +
                    "post p JOIN preferences pre ON p.category_id = pre.category_id " +
                    "JOIN user u ON u.id = pre.user_id WHERE u.email = ?1", nativeQuery = true)
    public List<Post> showPreferredPosts(String email);
}
