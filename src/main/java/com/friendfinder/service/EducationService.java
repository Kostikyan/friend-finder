package com.friendfinder.service;

import com.friendfinder.entity.Education;
import com.friendfinder.security.CurrentUser;

public interface EducationService {
    void saveEducation(Education education, CurrentUser currentUser);

}
