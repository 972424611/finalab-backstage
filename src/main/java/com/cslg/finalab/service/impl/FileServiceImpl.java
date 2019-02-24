package com.cslg.finalab.service.impl;

import com.cslg.finalab.common.FileOperation;
import com.cslg.finalab.dao.SysMemberMapper;
import com.cslg.finalab.dao.SysMemoryMapper;
import com.cslg.finalab.dao.SysProjectMapper;
import com.cslg.finalab.dao.SysWinningMapper;
import com.cslg.finalab.enums.MemberEnum;
import com.cslg.finalab.enums.ProjectEnum;
import com.cslg.finalab.enums.WinningEnum;
import com.cslg.finalab.exception.MemberException;
import com.cslg.finalab.exception.ProjectException;
import com.cslg.finalab.exception.WinningException;
import com.cslg.finalab.model.SysMember;
import com.cslg.finalab.model.SysMemory;
import com.cslg.finalab.model.SysWinning;
import com.cslg.finalab.service.FileService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Twilight
 * @date 2019-02-06 14:41
 */
@Service
public class FileServiceImpl implements FileService {

    @Value("${image.address}")
    private String imageAddress;

    @Value("${image.database.address}")
    private String imageDatabaseAddress;

    private final SysProjectMapper sysProjectMapper;

    private final SysMemberMapper sysMemberMapper;

    private final SysMemoryMapper sysMemoryMapper;

    private final SysWinningMapper sysWinningMapper;

    @Autowired
    public FileServiceImpl(SysProjectMapper sysProjectMapper, SysMemberMapper sysMemberMapper,
                           SysMemoryMapper sysMemoryMapper, SysWinningMapper sysWinningMapper) {
        this.sysProjectMapper = sysProjectMapper;
        this.sysMemberMapper = sysMemberMapper;
        this.sysMemoryMapper = sysMemoryMapper;
        this.sysWinningMapper = sysWinningMapper;
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void uploadProjectImage(MultipartFile multipartFile, int projectId) {
        if(sysProjectMapper.countProjectByPrimaryKey(projectId) != 1) {
            throw new ProjectException(ProjectEnum.PROJECT_NOT_FOUND);
        }
        FileOperation.checkImageSize(multipartFile.getSize());
        String format = FileOperation.checkFileNameAndGetFormat(multipartFile.getOriginalFilename());
        String imageAddress = FileOperation.checkFileSeparator(this.imageAddress);
        // eg: /Users/twilight/IdeaProjects/finalab/image/ + project/ + 100/ + coverImage.jpg
        String addressSuffix = "project" + File.separator +
                projectId + File.separator + "coverImage" + format;
        String pathName = imageAddress + addressSuffix;
        FileOperation.writeToFile(pathName, multipartFile);
        String imageDatabasePathName = imageDatabaseAddress + addressSuffix;
        sysProjectMapper.updateCoverImageByPrimaryKey(imageDatabasePathName, projectId);
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
            FileOperation.checkImageSize(multipartFiles[i].getSize());
            String format = FileOperation.checkFileNameAndGetFormat(multipartFiles[i].getOriginalFilename());
            String imageAddress = FileOperation.checkFileSeparator(this.imageAddress);
            // eg: /Users/twilight/IdeaProjects/finalab/image/ + project/ + 100/ + image1.jpg
            String addressSuffix = "project" + File.separator +
                    projectId + File.separator + "image" + (i + 1) + format;
            String pathName = imageAddress +addressSuffix;
            pathNameList.add(pathName);
            String imageDatabasePathName = imageDatabaseAddress + addressSuffix;
            paths.append(imageDatabasePathName);
            if(i != multipartFiles.length - 1) {
                paths.append(",");
            }
        }
        for(int i = 0; i < pathNameList.size(); i++) {
            FileOperation.writeToFile(pathNameList.get(i), multipartFiles[i]);
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
        FileOperation.checkImageSize(multipartFile.getSize());
        String format = FileOperation.checkFileNameAndGetFormat(multipartFile.getOriginalFilename());
        String imageAddress = FileOperation.checkFileSeparator(this.imageAddress);
        // eg: /Users/twilight/IdeaProjects/finalab/image/ + member/ + 2017/ + 201650080528.jpg
        String addressSuffix = "member" + File.separator +
                sysMember.getGrade() + File.separator +
                sysMember.getStuId() + format;
        String pathName = imageAddress + addressSuffix;
        FileOperation.writeToFile(pathName, multipartFile);
        String imageDatabasePathName = imageDatabaseAddress + addressSuffix;
        sysMemberMapper.updateHeadPortraitByPrimaryKey(imageDatabasePathName, memberId);
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
            FileOperation.checkImageSize(multipartFile.getSize());
            String format = FileOperation.checkFileNameAndGetFormat(multipartFile.getOriginalFilename());
            LocalDateTime localDateTime = LocalDateTime.now();
            int year = localDateTime.getYear();
            String imageAddress = FileOperation.checkFileSeparator(this.imageAddress);
            // eg: /Users/twilight/IdeaProjects/finalab/image/ + memory/ + 2016/ + 凡路年会/ + 1.jpg
            String addressSuffix = "memory" + File.separator +
                    year + File.separator + remark + File.separator + (i + 1) + format;
            String pathName = imageAddress + addressSuffix;
            pathNameList.add(pathName);
            sysMemory.setRemark(remark);
            String imageDatabasePathName = imageDatabaseAddress + addressSuffix;
            sysMemory.setPhoto(imageDatabasePathName);
            sysMemory.setTime(new Date());
            sysMemoryList.add(sysMemory);
        }
        for(int i = 0; i < pathNameList.size(); i++) {
            FileOperation.writeToFile(pathNameList.get(i), fileList.get(i));
        }
        sysMemoryMapper.batchInsert(sysMemoryList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void uploadWinningImage(MultipartFile multipartFile, Integer winningId) {
        SysWinning sysWinning = sysWinningMapper.selectByPrimaryKey(winningId);
        if(sysWinning == null) {
            throw new WinningException(WinningEnum.WINNING_NOT_FOUND);
        }
        FileOperation.checkImageSize(multipartFile.getSize());
        String format = FileOperation.checkFileNameAndGetFormat(multipartFile.getOriginalFilename());
        // eg: /Users/twilight/IdeaProjects/finalab/image/ + winning/ + 盲杖/ + 中国大学生计算机设计大赛.jpg
        /*
        String pathName = imageAddress + "winning" + File.separator +
                sysWinning.getName() + File.separator +
                sysWinning.getAwardName() + format;
        */
        // eg: /Users/twilight/IdeaProjects/finalab/image/ + winning/ + winningId.jpg
        String imageAddress = FileOperation.checkFileSeparator(this.imageAddress);
        String addressSuffix = "winning" + File.separator + winningId + format;
        String pathName = imageAddress + addressSuffix;
        FileOperation.writeToFile(pathName, multipartFile);
        String imageDatabasePathName = imageDatabaseAddress + addressSuffix;
        sysWinningMapper.updateAwardImageByPrimaryKey(imageDatabasePathName, winningId);
    }

}
