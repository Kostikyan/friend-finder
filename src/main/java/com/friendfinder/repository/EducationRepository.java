package com.friendfinder.repository;

import com.friendfinder.entity.Education;
import com.friendfinder.entity.WorkExperiences;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Integer> {


    List<Education> findAllByUserId(int userId);
}
