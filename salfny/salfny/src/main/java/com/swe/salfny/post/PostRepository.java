package com.swe.salfny.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value = "SELECT * FROM post p ORDER BY p.date LIMIT 3", nativeQuery = true)
    public List<Post> showRecentPosts();

    @Query(value = "SELECT p.* FROM " +
                    "post p Join preferences pre on p.category_id = pre.category_id " +
                    "join user u on u.id = pre.user_id where u.email = ?1", nativeQuery = true)
    public List<Post> showPreferredPosts(String email);
}
