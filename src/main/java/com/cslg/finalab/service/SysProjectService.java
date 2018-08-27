package com.cslg.finalab.service;

import com.cslg.finalab.vo.ProjectVo;

import java.util.List;

public interface SysProjectService {

    /**
     * 使用Set展示项目的时候顺序就有随机性
     * @return
     */
    List<ProjectVo> getAllProjectList();

}
