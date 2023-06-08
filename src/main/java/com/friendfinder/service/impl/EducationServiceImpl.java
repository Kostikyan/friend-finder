package com.friendfinder.service.impl;

import com.friendfinder.entity.Education;
import com.friendfinder.entity.User;
import com.friendfinder.repository.EducationRepository;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;

    @Override
    public void saveEducation(Education education, CurrentUser currentUser) {
            User user = currentUser.getUser();
            education.setUser(user);
            educationRepository.save(education);
    }
}
