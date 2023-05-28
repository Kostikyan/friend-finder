package com.friendfinder.service;

import com.friendfinder.entity.Interest;
import com.friendfinder.entity.Language;

import java.util.List;

public interface LanguageService {
    List<Language> findAllByUserId(int userId);
}
