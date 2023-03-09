package com.hungnguyen.blogweb.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Service
public class ImageService {
    static public String folder ="src/main/resources/static/photo";
    public void saveImage(MultipartFile image) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        byte[] bytes = image.getBytes();
        Path path = Paths.get(folder,image.getOriginalFilename());
        fileNames.append(image.getOriginalFilename());
        Files.write(path,bytes);
    }

    public void delete(String imagename) throws IOException {
        Path path =Paths.get(folder,imagename);
        Files.delete(path);
    }
}
