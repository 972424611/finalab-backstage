package com.cslg.finalab.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Twilight
 * @date 2019-02-06 13:56
 */
public interface UploadService {

    void uploadProjectImage(MultipartFile multipartFile, int projectId);

    void batchUploadProjectImage(MultipartFile[] multipartFiles, int projectId);

    void uploadMemberImage(MultipartFile multipartFile, Integer memberId);

    void batchUploadMemoryImage(List<MultipartFile> fileList, String remark);
}
