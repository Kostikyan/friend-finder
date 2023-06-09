package com.friendfinder.service;

import com.friendfinder.entity.Country;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

public interface MainService {

    List<Country> findAllCountries();

    @ResponseBody
    byte[] getImage(String imageName) throws IOException;

    @ResponseBody
    byte[] getProfilePic(String imageName) throws IOException;

    @ResponseBody
    byte[] getBgProfilePic(String imageName) throws IOException;
}
