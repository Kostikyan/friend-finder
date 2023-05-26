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

    private final CountryRepository countryRepository;

    @Override
    public List<Country> findAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public @ResponseBody byte[] getImage(String imageName) throws IOException {
        File file = new File(imageUploadPath + imageName);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            return IOUtils.toByteArray(fis);
        }
        return null;
    }
}
