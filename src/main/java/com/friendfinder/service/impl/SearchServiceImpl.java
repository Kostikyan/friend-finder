package com.friendfinder.service.impl;

import com.friendfinder.entity.User;
import com.friendfinder.repository.UserRepository;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final UserRepository userRepository;

    @Override
    public List<User> searchByKeyword(String keyword, CurrentUser currentUser) {
        Optional<List<User>> byNameContainsOrSurnameContains = userRepository.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(keyword,keyword);
        if (byNameContainsOrSurnameContains.isPresent()){
            List<User> users = byNameContainsOrSurnameContains.get();
            users.remove(currentUser.getUser());
        }
        return byNameContainsOrSurnameContains.orElse(null);
    }
}
