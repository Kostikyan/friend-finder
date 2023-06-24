package com.friendfinder.service;

import com.friendfinder.entity.Country;
import com.friendfinder.entity.User;
import com.friendfinder.security.CurrentUser;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> userFindAll();

    List<Country> findAllCountries();

    List<User> userForAddFriend(CurrentUser currentUser);

    void userRegister(User user);

    void updateUserPasswordById(String password, int id);

    Optional<User> findByEmail(String email);

    void save(User user);


}
