package com.friendfinder.service;

import com.friendfinder.entity.User;

import java.util.List;

public interface UserService {
    List<User> userFindAll();

    void userSave(User user);

}
