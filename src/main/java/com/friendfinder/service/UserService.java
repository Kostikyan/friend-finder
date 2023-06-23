package com.friendfinder.service;

import com.friendfinder.entity.Country;
import com.friendfinder.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> userFindAll();

    List<Country> findAllCountries();

    void userRegister(User user);

    void updateUserPasswordById(String password, int id);

    Optional<User> findByEmail(String email);

    void save(User user);


}
