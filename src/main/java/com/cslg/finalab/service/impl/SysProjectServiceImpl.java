package com.cslg.finalab.service.impl;

import com.cslg.finalab.dao.SysProjectMapper;
import com.cslg.finalab.model.SysProject;
import com.cslg.finalab.service.SysProjectService;
import com.cslg.finalab.vo.ProjectVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysProjectServiceImpl implements SysProjectService {

    @Autowired
    private SysProjectMapper sysProjectMapper;

    @Override
    public List<ProjectVo> getAllProjectList() {
        List<SysProject> sysProjectList = sysProjectMapper.selectAll();
        List<ProjectVo> projectVoList = new ArrayList<>();
        for(SysProject sysProject : sysProjectList) {
            ProjectVo projectVo = new ProjectVo();
            BeanUtils.copyProperties(sysProject, projectVo);
        }
        return projectVoList;
    }
}
