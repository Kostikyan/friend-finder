package com.friendfinder.service.impl;

import com.friendfinder.entity.User;
import com.friendfinder.repository.UserRepository;
import com.friendfinder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public List<User> userFindAll() {
        return userRepository.findAll();
    }

    @Override
    public void userSave(User user) {
        Optional<User> userFromDB = userRepository.findByEmail(user.getEmail());
        if (userFromDB.isEmpty()) {
            String password = user.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            userRepository.save(user);
        }
    }

}
