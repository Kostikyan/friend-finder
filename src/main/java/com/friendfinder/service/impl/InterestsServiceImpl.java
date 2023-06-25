package com.friendfinder.service.impl;

import com.friendfinder.entity.Interest;
import com.friendfinder.repository.InterestsRepository;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.InterestsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestsServiceImpl implements InterestsService {

    private final InterestsRepository interestsRepository;

    @Override
    public List<Interest> findAllByUserId(int userId) {
        return interestsRepository.findAllByUserId(userId);
    }

    @Override
    public void interestSave(String interest, CurrentUser currentUser) {
        interestsRepository.save(Interest.builder()
                .interest(interest)
                .user(currentUser.getUser())
                .build());
    }
}
