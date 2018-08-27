package com.cslg.finalab.service.impl;

import com.cslg.finalab.dao.SysProjectMapper;
import com.cslg.finalab.model.SysProjectWithBLOBs;
import com.cslg.finalab.service.SysProjectService;
import com.cslg.finalab.vo.ProjectVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysProjectServiceImpl implements SysProjectService {

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
            projectVo.setName(sysProjectWithBLOBs.getName());
            projectVo.setCategory(sysProjectWithBLOBs.getCategory());
            projectVo.setIntroduction(sysProjectWithBLOBs.getIntroduction());
            if(StringUtils.isNotBlank(sysProjectWithBLOBs.getCoverImage())) {
                projectVo.setCoverImage(serverAddress + sysProjectWithBLOBs.getCoverImage());
            }
            String members = sysProjectWithBLOBs.getMembers();
            List<String> memberList = null;
            if(StringUtils.isNotBlank(members)) {
                memberList = Arrays.asList(StringUtils.split(members, " "));
            }
            projectVo.setMemberList(memberList);
            String images = sysProjectWithBLOBs.getImages();
            List<String> imageList = null;
            if(StringUtils.isNotBlank(images)) {
                imageList = Arrays.asList(StringUtils.split(images, ","));
                for(int i = 0; i < imageList.size(); i++) {
                    imageList.set(i, serverAddress + imageList.get(i));
                }
            }
            projectVo.setImageList(imageList);
            projectVoList.add(projectVo);
        }
        // 随机排序
        if(projectVoList.size() > 1) {
            Collections.shuffle(projectVoList);
        }
        return projectVoList;
    }
}
