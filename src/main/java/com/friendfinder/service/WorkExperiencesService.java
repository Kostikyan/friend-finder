package com.friendfinder.service;

import com.friendfinder.entity.WorkExperiences;

import java.util.List;

public interface WorkExperiencesService {
    List<WorkExperiences> findAllByUserId(int userId);
}
