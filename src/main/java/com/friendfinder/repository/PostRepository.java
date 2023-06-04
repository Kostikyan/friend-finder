package com.friendfinder.repository;

import com.friendfinder.entity.Post;
import com.friendfinder.entity.types.LikeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {


    @Query(value ="select * from post_like where like_status = LIKE", nativeQuery = true)
    List<LikeStatus> findPostLikeCountByLikeStatus(LikeStatus likeStatus);
    List<Post> findPostByUser_id(int id);
}
