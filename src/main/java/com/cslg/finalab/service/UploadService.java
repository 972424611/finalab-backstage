package com.cslg.finalab.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Twilight
 * @date 2019-02-06 13:56
 */
public interface UploadService {

    /**
     * 上传项目封面图片
     * @param projectId 项目id
     */
    void uploadProjectImage(MultipartFile multipartFile, int projectId);

    /**
     * 批量上传项目图片
     * @param projectId 项目id
     */
    void batchUploadProjectImage(MultipartFile[] multipartFiles, int projectId);

    /**
     * 上传成员图片
     * @param memberId 成员id
     */
    void uploadMemberImage(MultipartFile multipartFile, Integer memberId);

    /**
     * 批量上传团队活动图片
     * @param remark 图片说明
     */
    void batchUploadMemoryImage(List<MultipartFile> fileList, String remark);

    /**
     * 上传获奖图片
     * @param winningId 获奖项目id
     */
    void uploadWinningImage(MultipartFile multipartFile, Integer winningId);
}
