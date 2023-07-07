package com.friendfinder.util;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@UtilityClass
public class ImageUtil {
    Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    public static String uploadImage(MultipartFile multipartFile, String path) {
        String fileName;
        try {
            if (multipartFile != null && !multipartFile.isEmpty()) {
                fileName = System.nanoTime() + "_" + multipartFile.getOriginalFilename();
                File file = new File(path + fileName);
                if(imageFilerChain(file)) {
                    multipartFile.transferTo(file);
                    return fileName;
                }
                throw new IOException("Wrong file format");
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    private static boolean imageFilerChain(File file){
        return file.getName().endsWith(".img") || file.getName().endsWith(".jpg") || file.getName().endsWith(".mp4") ||
                file.getName().endsWith(".jpeg") || file.getName().endsWith(".gif") || file.getName().endsWith(".svg") ||
                file.getName().endsWith(".webp");
    }
}
