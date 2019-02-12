package com.cslg.finalab.service.impl;

import com.cslg.finalab.common.BeanValidator;
import com.cslg.finalab.common.FileOperation;
import com.cslg.finalab.dao.SysProjectMapper;
import com.cslg.finalab.enums.FileEnum;
import com.cslg.finalab.enums.ProjectEnum;
import com.cslg.finalab.exception.FileException;
import com.cslg.finalab.exception.ProjectException;
import com.cslg.finalab.model.SysProject;
import com.cslg.finalab.model.SysProjectWithBLOBs;
import com.cslg.finalab.param.ProjectParam;
import com.cslg.finalab.service.ProjectService;
import com.cslg.finalab.vo.ProjectDetailVo;
import com.cslg.finalab.vo.ProjectVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Value("${server_address}")
    private String serverAddress;

    @Autowired
    private SysProjectMapper sysProjectMapper;

    @Override
    public List<ProjectVo> getAllProjectList() {
        List<SysProjectWithBLOBs> sysProjectWithBLOBsList = sysProjectMapper.selectAll();
        List<ProjectVo> projectVoList = new ArrayList<>();
        for(SysProjectWithBLOBs sysProjectWithBLOBs : sysProjectWithBLOBsList) {
            ProjectVo projectVo = new ProjectVo();
            projectVo.setId(sysProjectWithBLOBs.getId());
            projectVo.setName(sysProjectWithBLOBs.getName());
            projectVo.setCategory(sysProjectWithBLOBs.getCategory());
            if(StringUtils.isNotBlank(sysProjectWithBLOBs.getCoverImage())) {
                projectVo.setCoverImage(serverAddress + sysProjectWithBLOBs.getCoverImage());
            }
            projectVoList.add(projectVo);
        }
        // 随机排序
        if(projectVoList.size() > 1) {
            Collections.shuffle(projectVoList);
        }
        return projectVoList;
    }

    @Override
    public ProjectDetailVo getProjectDetailById(Integer id) {
        SysProjectWithBLOBs sysProjectWithBLOBs = sysProjectMapper.selectByPrimaryKey(id);
        ProjectDetailVo projectDetailVo = new ProjectDetailVo();
        if(sysProjectWithBLOBs == null) {
            return projectDetailVo;
        }
        BeanUtils.copyProperties(sysProjectWithBLOBs, projectDetailVo);
        String members = sysProjectWithBLOBs.getMembers();
        List<String> memberList = null;
        if(StringUtils.isNotBlank(members)) {
            memberList = Arrays.asList(StringUtils.split(members, " "));
        }
        projectDetailVo.setMemberList(memberList);
        String images = sysProjectWithBLOBs.getImages();
        List<String> imageList = null;
        if(StringUtils.isNotBlank(images)) {
            imageList = Arrays.asList(StringUtils.split(images, ","));
            for(int i = 0; i < imageList.size(); i++) {
                imageList.set(i, serverAddress + imageList.get(i));
            }
        }
        projectDetailVo.setImageList(imageList);
        return projectDetailVo;
    }

    @Override
    public int saveProject(ProjectParam projectParam) {
        BeanValidator.check(projectParam);
        // 判断该项目是否存在
        if(sysProjectMapper.countProjectByProjectName(projectParam.getName()) > 0) {
            throw new ProjectException(ProjectEnum.PROJECT_ALREADY_EXISTS);
        }
        // 判断技术负责人和策划负责人是否为同一个人
        if(projectParam.getChiefArtisan().equals(projectParam.getChiefPlanner())) {
            throw new ProjectException(ProjectEnum.PLANNER_ARTISAN_NOT_THE_SAME_PEOPLE);
        }
        if(StringUtils.isBlank(projectParam.getVersion())) {
            projectParam.setVersion("v1.0");
        }
        SysProjectWithBLOBs sysProjectWithBLOBs = new SysProjectWithBLOBs();
        BeanUtils.copyProperties(projectParam, sysProjectWithBLOBs);
        sysProjectMapper.insertSelectiveAndGetProjectId(sysProjectWithBLOBs);
        return sysProjectWithBLOBs.getId();
    }

    @Override
    public void deleteProjectById(Integer[] projectIds) {
        if(projectIds == null || projectIds.length == 0) {
            return;
        }
        List<SysProject> sysProjectList = sysProjectMapper.selectByPrimaryKeys(projectIds);
        // 删除该项目的相关图片
        for(SysProject sysProject : sysProjectList) {
            if(StringUtils.isNotBlank(sysProject.getCoverImage())) {
                File file = new File(sysProject.getCoverImage());
                FileOperation.deleteDirectory(file.getParentFile().getAbsolutePath());
            }
        }
        sysProjectMapper.batchDeleteByPrimaryKey(projectIds);
    }

    @Override
    public void updateProjectById(ProjectParam projectParam) {
        // 检查项目名是否重复
        if(StringUtils.isNotBlank(projectParam.getName())) {
            String name = projectParam.getName();
            if(sysProjectMapper.countProjectByProjectName(name) > 0) {
                throw new ProjectException(ProjectEnum.PROJECT_ALREADY_EXISTS);
            }
        }
        // 判断技术负责人和策划负责人是否为同一个人
        if(projectParam.getChiefArtisan().equals(projectParam.getChiefPlanner())) {
            throw new ProjectException(ProjectEnum.PLANNER_ARTISAN_NOT_THE_SAME_PEOPLE);
        }
        SysProjectWithBLOBs sysProjectWithBLOBs = new SysProjectWithBLOBs();
        BeanUtils.copyProperties(projectParam, sysProjectWithBLOBs);
        sysProjectMapper.updateByPrimaryKeySelective(sysProjectWithBLOBs);
    }

}
