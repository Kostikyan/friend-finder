package com.friendfinder.service;

import com.friendfinder.entity.Interest;
import java.util.List;

public interface InterestsService {
    List<Interest> findAllByUserId(int userId);
}
