package com.friendfinder.service.impl;

import com.friendfinder.entity.Country;
import com.friendfinder.repository.CountryRepository;
import com.friendfinder.service.MainService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    @Value("${post.upload.image.path}")
    private String imageUploadPath;

    @Value("${post.video.upload.image.path}")
    private String videoUploadPath;

    @Value("${user.profile.picture.path}")
    private String userProfilePicPath;

    @Value("${user.profile.background-picture.path}")
    private String userBgProfilePicPath;

    private final CountryRepository countryRepository;

    @Override
    public List<Country> findAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public @ResponseBody byte[] getImage(String imageName) throws IOException {
        File fileImageByDownload = new File(imageUploadPath + imageName);
        if (fileImageByDownload.exists()) {
            FileInputStream fis = new FileInputStream(fileImageByDownload);
            return IOUtils.toByteArray(fis);
        } else {
            File fileVideoByDownload = new File(videoUploadPath + imageName);
            if (fileVideoByDownload.exists()) {
                FileInputStream fis = new FileInputStream(fileVideoByDownload);
                return IOUtils.toByteArray(fis);
            }
        }
        return null;
    }

    @Override
    public @ResponseBody byte[] getProfilePic(String imageName) throws IOException {
        File file = new File(userProfilePicPath + imageName);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            return IOUtils.toByteArray(fis);
        }
        return null;
    }

    @Override
    public @ResponseBody byte[] getBgProfilePic(String imageName) throws IOException {
        File file = new File(userBgProfilePicPath + imageName);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            return IOUtils.toByteArray(fis);
        }
        return null;
    }
}
