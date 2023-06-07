package com.friendfinder.service;

import com.friendfinder.entity.WorkExperiences;
import com.friendfinder.security.CurrentUser;

import java.util.List;

public interface WorkExperiencesService {
    List<WorkExperiences> findAllByUserId(int userId);

    void saveWorkExperiences(WorkExperiences workExperiences, CurrentUser currentUser);
}
