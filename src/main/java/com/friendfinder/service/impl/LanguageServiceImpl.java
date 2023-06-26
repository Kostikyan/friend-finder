package com.friendfinder.service.impl;

import com.friendfinder.entity.Language;
import com.friendfinder.repository.LanguageRepository;
import com.friendfinder.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    @Override
    public List<Language> findAllByUserId(int userId) {
        return languageRepository.findAllByUserId(userId);
    }
}
