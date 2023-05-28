package com.friendfinder.repository;

import com.friendfinder.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findPostByUser_id(int id);

}
