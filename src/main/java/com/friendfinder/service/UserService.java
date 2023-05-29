package com.friendfinder.service;

import com.friendfinder.entity.Country;
import com.friendfinder.entity.User;

import java.util.List;

public interface UserService {
    List<User> userFindAll();

    List<Country> findAllCountries();

    void userSave(User user);

    void updateUserPasswordById(String password, int id);


}
