package com.friendfinder.repository;

import com.friendfinder.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    Optional<Chat> findByCurrentUserIdAndAnotherUserId(int firstId, int secondId);
    List<Chat> findAllByCurrentUserId(int currentUserId);
    List<Chat> findAllByAnotherUserId(int anotherUserId);
}
