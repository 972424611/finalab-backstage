package com.cslg.finalab.service;

import com.cslg.finalab.param.ProjectParam;
import com.cslg.finalab.vo.ProjectDetailVo;
import com.cslg.finalab.vo.ProjectVo;

import java.util.List;

public interface ProjectService {

    /**
     * 使用Set展示项目的时候顺序就有随机性
     * @return List<ProjectVo>
     */
    List<ProjectVo> getAllProjectList();

    /**
     * 获取项目的详细信息
     * @param id 项目id
     * @return ProjectDetailVo
     */
    ProjectDetailVo getProjectDetailById(Integer id);

    /**
     * 保存项目
     * @param projectParam 请求体数据
     * @return 项目的id
     */
    int saveProject(ProjectParam projectParam);
}
