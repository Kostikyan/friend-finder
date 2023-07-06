package com.friendfinder.repository;

import com.friendfinder.entity.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserActivityRepository extends JpaRepository<UserActivity,Long> {
    Optional<List<UserActivity>> findTop4ByUserIdOrderByDateTimeDesc(int id);
}
