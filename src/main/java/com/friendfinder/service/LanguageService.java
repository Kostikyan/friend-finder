package com.friendfinder.service;

import com.friendfinder.entity.Language;

import java.util.List;

public interface LanguageService {
    List<Language> findAllByUserId(int userId);

    void save(Language lang);
}
