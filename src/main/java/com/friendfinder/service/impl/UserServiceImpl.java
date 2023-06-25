package com.friendfinder.service.impl;

import com.friendfinder.entity.Country;
import com.friendfinder.entity.User;
import com.friendfinder.repository.CountryRepository;
import com.friendfinder.repository.UserRepository;
import com.friendfinder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CountryRepository countryRepository;

    @Override
    public List<User> userFindAll() {
        return userRepository.findAll();
    }

    @Override
    public List<Country> findAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public void userRegister(User user) {
        Optional<User> userFromDB = userRepository.findByEmail(user.getEmail());
        if (userFromDB.isEmpty()) {
            String password = user.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            user.setEnabled(false);
            userRepository.save(user);
        }
    }

    @Override
    public void updateUserPasswordById(String password, int id) {
        userRepository.updateUserPasswordById(password, id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(int id){
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAllExceptCurrentUser(int currentUserId){
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getId() != currentUserId)
                .collect(Collectors.toList());
    }
}
