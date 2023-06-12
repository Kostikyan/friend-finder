package com.friendfinder.repository;

import com.friendfinder.entity.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserImageRepository extends JpaRepository<UserImage, Integer> {


    List<UserImage> findUserImageAllByUserId(int userId);

    List<UserImage> findByUserId(int userId);
}
