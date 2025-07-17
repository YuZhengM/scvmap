package com.spring.boot.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadFile(MultipartFile file) throws IOException;

    void deleteFile(String fileId);

    boolean existFile(String filepath);

}
