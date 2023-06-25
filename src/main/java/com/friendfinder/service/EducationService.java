package com.friendfinder.service;

import com.friendfinder.entity.Education;
import com.friendfinder.entity.User;
import com.friendfinder.security.CurrentUser;

import java.util.List;

public interface EducationService {


    List<Education> educationByUserId(User user);

    void saveEducation(Education education, CurrentUser currentUser);

}
