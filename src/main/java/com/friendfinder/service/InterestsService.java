package com.friendfinder.service;

import com.friendfinder.entity.Interest;
import com.friendfinder.entity.WorkExperiences;

import java.util.List;

public interface InterestsService {
    List<Interest> findAllByUserId(int userId);
}
