package com.swe.salfny.Model.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SearchRepository extends JpaRepository<Post, Integer> {
    @Query(value =  "SELECT * FROM post p WHERE p.title LIKE %:word% OR  p.description LIKE %:word% "+
            " OR p.price LIKE %:word% ",nativeQuery = true)
    public List<Post> searchLike(@Param("word") String word);


    @Query(value =  "SELECT p.* FROM post p JOIN category c ON p.category_id = c.id WHERE c.name LIKE %:category%  "+
            "AND ( p.title LIKE %:word% OR p.description LIKE %:word% )",nativeQuery = true)
    public List<Post> searchByCategoryLike(@Param("category") String category,@Param("word") String word);

    @Query(value =  "SELECT p.* FROM post p JOIN category c ON p.category_id = c.id" , nativeQuery = true)
    public List<Post> allCategory();



}
