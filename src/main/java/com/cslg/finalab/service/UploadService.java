package com.cslg.finalab.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Twilight
 * @date 2019-02-06 13:56
 */
public interface UploadService {

    void uploadImage(MultipartFile multipartFile, int projectId);

    void batchUploadImage(MultipartFile[] multipartFile, int projectId);
}
