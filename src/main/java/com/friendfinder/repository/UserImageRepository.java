package com.friendfinder.repository;

import com.friendfinder.entity.UserImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserImageRepository extends JpaRepository<UserImage, Integer> {


    List<UserImage> findUserImageAllByUserId(int userId);

    List<UserImage> findByUserId(int userId);

    Page<UserImage> findUserImagesByUserIdIn(List<Integer> userImageId, Pageable pageable);
}
