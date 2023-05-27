package com.friendfinder.service.impl;

import com.friendfinder.entity.WorkExperiences;
import com.friendfinder.repository.WorkExperiencesRepository;
import com.friendfinder.service.WorkExperiencesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkExperiencesServiceImpl implements WorkExperiencesService {
    private final WorkExperiencesRepository workExperiencesRepository;
    @Override
    public List<WorkExperiences> findAllByUserId(int userId) {
        return workExperiencesRepository.findAllByUserId(userId);
    }
}
