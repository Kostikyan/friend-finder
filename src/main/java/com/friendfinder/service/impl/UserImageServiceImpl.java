package com.friendfinder.service.impl;

import com.friendfinder.entity.User;
import com.friendfinder.entity.UserImage;
import com.friendfinder.repository.UserImageRepository;
import com.friendfinder.repository.UserRepository;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.UserImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserImageServiceImpl implements UserImageService {

    private final UserImageRepository userImageRepository;
    private final UserRepository userRepository;

    @Override
    public List<UserImage> getUserImageById(int userId) {
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isPresent()) {
            User user = byId.get();
            return userImageRepository.findByUserId(user.getId());
        }
        return null;
    }

    @Override
    public void userImageSave(UserImage userImage, CurrentUser currentUser) {
        userImageRepository.save(UserImage.builder()
                .user(currentUser.getUser())
                .imageName(currentUser.getUser().getProfilePicture())
                .build());
    }

    @Override
    public void deleteUserImageById(int id) {
        userImageRepository.deleteById(id);
    }
}
