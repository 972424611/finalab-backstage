package com.cslg.finalab.service.impl;

import com.cslg.finalab.dao.SysMemberMapper;
import com.cslg.finalab.dao.SysProjectMapper;
import com.cslg.finalab.enums.MemberEnum;
import com.cslg.finalab.enums.ProjectEnum;
import com.cslg.finalab.enums.UploadEnum;
import com.cslg.finalab.exception.MemberException;
import com.cslg.finalab.exception.ProjectException;
import com.cslg.finalab.exception.UploadException;
import com.cslg.finalab.model.SysMember;
import com.cslg.finalab.service.UploadService;
import com.google.common.collect.Sets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Twilight
 * @date 2019-02-06 14:41
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Value("${image.address}")
    private String imageAddress;

    /**  图片最大大小 */
    private static final long MAXSIZE = 5242880;

    private static Set<String> imageFormatSet = Sets.newHashSet();

    static {
        imageFormatSet.add(".jpg");
        imageFormatSet.add(".png");
    }

    @Autowired
    private SysProjectMapper sysProjectMapper;

    @Autowired
    private SysMemberMapper sysMemberMapper;

    private File createFile(String pathName) {
        File file = new File(pathName);
        if(!file.getParentFile().exists()) {
            if(!file.getParentFile().mkdirs()) {
                throw new UploadException(UploadEnum.CREATE_FILE_FAILED);
            }
        }
        if(!file.exists()) {
            try {
                if(file.createNewFile()) {
                    return file;
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new UploadException(UploadEnum.CREATE_FILE_FAILED);
            }
        }
        return file;
    }

    private void checkImageSize(long size) {
        // 设置上传的最大值5MB，5*1024*1024
        if(size > MAXSIZE) {
            throw new UploadException(UploadEnum.IMAGE_TOO_LARGE);
        }
    }

    private String checkFileNameAndGetFormat(String fileName) {
        if(StringUtils.isBlank(fileName)) {
            throw new UploadException(UploadEnum.IMAGE_FORMAT_ERROR);
        }
        String format = fileName.substring(fileName.lastIndexOf("."));
        // 检查格式
        if(!imageFormatSet.contains(format)) {
            throw new UploadException(UploadEnum.IMAGE_FORMAT_ERROR);
        }
        return format;
    }

    private void writeToFile(String pathName, MultipartFile multipartFile) {
        File file = createFile(pathName);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new UploadException(UploadEnum.SERVER_BUSY);
        }
    }

    @Override
    @Transactional
    public void uploadProjectImage(MultipartFile multipartFile, int projectId) {
        if(sysProjectMapper.countProjectByPrimaryKey(projectId) != 1) {
            throw new ProjectException(ProjectEnum.PROJECT_NOT_FOUND);
        }
        checkImageSize(multipartFile.getSize());
        String format = checkFileNameAndGetFormat(multipartFile.getOriginalFilename());
        String pathName = imageAddress + "project/" + projectId + "/coverImage" + format;
        writeToFile(pathName, multipartFile);
        sysProjectMapper.updateCoverImageByPrimaryKey(pathName, projectId);
    }

    @Override
    @Transactional
    public void batchUploadProjectImage(MultipartFile[] multipartFiles, int projectId) {
        if(sysProjectMapper.countProjectByPrimaryKey(projectId) != 1) {
            throw new ProjectException(ProjectEnum.PROJECT_NOT_FOUND);
        }
        StringBuilder paths = new StringBuilder();
        for(int i = 0; i < multipartFiles.length; i++) {
            checkImageSize(multipartFiles[i].getSize());
            String format = checkFileNameAndGetFormat(multipartFiles[i].getOriginalFilename());
            String pathName = imageAddress + "project/" + projectId + "/image" + i + format;
            writeToFile(pathName, multipartFiles[i]);
            paths.append(pathName);
            if(i != multipartFiles.length - 1) {
                paths.append(",");
            }
        }
        sysProjectMapper.updateImagesByPrimaryKey(paths.toString(), projectId);
    }

    @Override
    public void uploadMemberImage(MultipartFile multipartFile, Integer memberId) {
        SysMember sysMember = sysMemberMapper.selectByPrimaryKey(memberId);
        if(sysMember == null) {
            throw new MemberException(MemberEnum.MEMBER_NOT_FOUND);
        }
        checkImageSize(multipartFile.getSize());
        String format = checkFileNameAndGetFormat(multipartFile.getOriginalFilename());
        String pathName = imageAddress + "member" + "/" +
                sysMember.getGrade() + "/" +
                sysMember.getStuId() + format;
        writeToFile(pathName, multipartFile);
        sysMemberMapper.updateHeadPortraitByPrimaryKey(pathName, memberId);
    }
}
