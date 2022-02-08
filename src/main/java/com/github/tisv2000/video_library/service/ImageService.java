package com.github.tisv2000.video_library.service;

import com.github.tisv2000.video_library.util.PropertiesUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageService {

    private static final ImageService INSTANCE = new ImageService();

    private final String basePath = PropertiesUtils.get("image.base.url");

    @SneakyThrows
    public void upload(String imagePath, InputStream imageContent) {
        var imageFullPath = Path.of(basePath, imagePath);
        try(imageContent) {
            Files.createDirectories(imageFullPath.getParent());
            Files.write(imageFullPath, imageContent.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    @SneakyThrows
    public byte[] downloadImage(String imagePath) {
        return Files.readAllBytes(Path.of(basePath, imagePath));
    }

    public static ImageService getInstance() {
        return INSTANCE;
    }
}
