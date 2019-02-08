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

    /**
     * 根据项目id删除项目
     * @param projectIds 项目ids
     */
    void deleteProjectById(Integer[] projectIds);

    /**
     * 更新项目，只更新不为null的属性。
     * @param projectParam 对象中只包含项目改变的属性
     */
    void updateProjectById(ProjectParam projectParam);
}
