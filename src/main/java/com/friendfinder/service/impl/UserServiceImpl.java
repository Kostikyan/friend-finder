package com.friendfinder.service.impl;

import com.friendfinder.dto.userDto.UserRegisterRequestDto;
import com.friendfinder.entity.Country;
import com.friendfinder.entity.User;
import com.friendfinder.mapper.UserMapper;
import com.friendfinder.repository.CountryRepository;
import com.friendfinder.repository.UserRepository;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.FriendRequestService;
import com.friendfinder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CountryRepository countryRepository;
    private final FriendRequestService friendRequestService;
    private final UserMapper userMapper;
    private final MailService mailService;
    @Value("${site.url}")
    String siteUrl;

    @Override
    public List<User> userFindAll() {
        return userRepository.findAll();
    }


    @Override
    public List<User> userForAddFriend(CurrentUser currentUser) {
        List<User> users = userFindAll();
        List<User> userForAddFriend = new ArrayList<>();
        for (User user : users) {
            if (friendRequestService.findBySenderIdAndReceiverId(user.getId(), currentUser.getUser().getId()) == null &&
                    user.getId() != currentUser.getUser().getId() && friendRequestService.findBySenderIdAndReceiverId(currentUser.getUser().getId(),
                    user.getId()) == null) {
                userForAddFriend.add(user);
            }
        }
        return userForAddFriend;
    }

    @Override
    public List<Country> findAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public void userRegister(UserRegisterRequestDto dto) {
        User user = userMapper.map(dto);

        Optional<User> userFromDB = userRepository.findByEmail(user.getEmail());
        if (userFromDB.isEmpty()) {
            String password = user.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            user.setEnabled(false);
            UUID uuid = UUID.randomUUID();
            user.setToken(uuid.toString());
            mailService.sendMail(user.getEmail(), "Verify Email",
                    "Hi " + user.getName() + "!\nPlease verify your email by clicking on this URL:\n " +
                            siteUrl + "/verify?email=" + user.getEmail() + "&token=" + uuid
            );
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
    public Optional<User> findUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAllExceptCurrentUser(int currentUserId) {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getId() != currentUserId)
                .collect(Collectors.toList());
    }
}
