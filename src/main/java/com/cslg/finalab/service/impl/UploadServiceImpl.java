package com.cslg.finalab.service.impl;

import com.cslg.finalab.dao.SysMemberMapper;
import com.cslg.finalab.dao.SysMemoryMapper;
import com.cslg.finalab.dao.SysProjectMapper;
import com.cslg.finalab.enums.MemberEnum;
import com.cslg.finalab.enums.ProjectEnum;
import com.cslg.finalab.enums.UploadEnum;
import com.cslg.finalab.exception.MemberException;
import com.cslg.finalab.exception.ProjectException;
import com.cslg.finalab.exception.UploadException;
import com.cslg.finalab.model.SysMember;
import com.cslg.finalab.model.SysMemory;
import com.cslg.finalab.service.UploadService;
import com.google.common.collect.Sets;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

    private final SysProjectMapper sysProjectMapper;

    private final SysMemberMapper sysMemberMapper;

    private final SysMemoryMapper sysMemoryMapper;

    @Autowired
    public UploadServiceImpl(SysProjectMapper sysProjectMapper,
                             SysMemberMapper sysMemberMapper,
                             SysMemoryMapper sysMemoryMapper) {
        this.sysProjectMapper = sysProjectMapper;
        this.sysMemberMapper = sysMemberMapper;
        this.sysMemoryMapper = sysMemoryMapper;
    }

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
    @Transactional(rollbackFor = Exception.class)
    public void uploadProjectImage(MultipartFile multipartFile, int projectId) {
        if(sysProjectMapper.countProjectByPrimaryKey(projectId) != 1) {
            throw new ProjectException(ProjectEnum.PROJECT_NOT_FOUND);
        }
        checkImageSize(multipartFile.getSize());
        String format = checkFileNameAndGetFormat(multipartFile.getOriginalFilename());
        // eg: /Users/twilight/IdeaProjects/finalab/image/ + project/ + 100/ + coverImage.jpg
        String pathName = imageAddress + "project/" + projectId + "/coverImage" + format;
        writeToFile(pathName, multipartFile);
        sysProjectMapper.updateCoverImageByPrimaryKey(pathName, projectId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchUploadProjectImage(MultipartFile[] multipartFiles, int projectId) {
        if(sysProjectMapper.countProjectByPrimaryKey(projectId) != 1) {
            throw new ProjectException(ProjectEnum.PROJECT_NOT_FOUND);
        }
        StringBuilder paths = new StringBuilder();
        // 写入文件要和检查文件格式分开，所以创建一个数组保存要写入的路径
        List<String> pathNameList = new ArrayList<>();
        for(int i = 0; i < multipartFiles.length; i++) {
            checkImageSize(multipartFiles[i].getSize());
            String format = checkFileNameAndGetFormat(multipartFiles[i].getOriginalFilename());
            // eg: /Users/twilight/IdeaProjects/finalab/image/ + project/ + 100/ + image1.jpg
            String pathName = imageAddress + "project/" + projectId + "/image" + (i + 1) + format;
            pathNameList.add(pathName);
            paths.append(pathName);
            if(i != multipartFiles.length - 1) {
                paths.append(",");
            }
        }
        for(int i = 0; i < pathNameList.size(); i++) {
            writeToFile(pathNameList.get(i), multipartFiles[i]);
        }
        sysProjectMapper.updateImagesByPrimaryKey(paths.toString(), projectId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void uploadMemberImage(MultipartFile multipartFile, Integer memberId) {
        SysMember sysMember = sysMemberMapper.selectByPrimaryKey(memberId);
        if(sysMember == null) {
            throw new MemberException(MemberEnum.MEMBER_NOT_FOUND);
        }
        checkImageSize(multipartFile.getSize());
        String format = checkFileNameAndGetFormat(multipartFile.getOriginalFilename());
        // eg: /Users/twilight/IdeaProjects/finalab/image/ + member/ + 2017/ + 201650080528.jpg
        String pathName = imageAddress + "member" + "/" +
                sysMember.getGrade() + "/" +
                sysMember.getStuId() + format;
        writeToFile(pathName, multipartFile);
        sysMemberMapper.updateHeadPortraitByPrimaryKey(pathName, memberId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchUploadMemoryImage(List<MultipartFile> fileList, String remark) {
        if(fileList == null || fileList.size() == 0) {
            return;
        }
        // 如果图片没有备注
        if(StringUtils.isBlank(remark)) {
            remark = "other";
        }
        // 写入文件要和检查文件格式分开，所以创建一个数组保存要写入的路径
        List<String> pathNameList = new ArrayList<>();
        List<SysMemory> sysMemoryList = new ArrayList<>();
        for(int i = 0; i < fileList.size(); i++) {
            SysMemory sysMemory = new SysMemory();
            MultipartFile multipartFile = fileList.get(i);
            checkImageSize(multipartFile.getSize());
            String format = checkFileNameAndGetFormat(multipartFile.getOriginalFilename());
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            // eg: /Users/twilight/IdeaProjects/finalab/image/ + memory/ + 2016/ + 凡路年会/ + 1.jpg
            String pathName = imageAddress + "memory" + "/" +
                    year + "/" + remark + "/" + (i + 1) + format;
            pathNameList.add(pathName);
            sysMemory.setRemark(remark);
            sysMemory.setPhoto(pathName);
            sysMemory.setTime(new Date());
            sysMemoryList.add(sysMemory);
        }
        for(int i = 0; i < pathNameList.size(); i++) {
            writeToFile(pathNameList.get(i), fileList.get(i));
        }
        sysMemoryMapper.batchInsert(sysMemoryList);
    }

}
