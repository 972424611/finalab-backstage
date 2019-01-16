package com.cslg.finalab.service;

import com.cslg.finalab.vo.ProjectDetailVo;
import com.cslg.finalab.vo.ProjectVo;

import java.util.List;

public interface ProjectService {

    /**
     * 使用Set展示项目的时候顺序就有随机性
     * @return
     */
    List<ProjectVo> getAllProjectList();

    ProjectDetailVo getProjectDetailById(Integer id);
}
