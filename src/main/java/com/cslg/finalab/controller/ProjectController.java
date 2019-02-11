package com.cslg.finalab.controller;

import com.cslg.finalab.beans.JsonData;
import com.cslg.finalab.param.ProjectParam;
import com.cslg.finalab.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/sys/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * 获取所有项目信息
     * @return 项目的信息
     */
    @ResponseBody
    @RequestMapping(value = "/getAll")
    public JsonData getAll() {
        return JsonData.success(projectService.getAllProjectList());
    }

    /**
     * 根据项目id，获取项目详细信息
     * @param id 项目id
     * @return 项目详细信息
     */
    @ResponseBody
    @RequestMapping(value = "/detail")
    public JsonData detail(@RequestParam(value = "id") Integer id) {
        return JsonData.success(projectService.getProjectDetailById(id));
    }

    /**
     * 增加项目
     * @param projectParam 新增加的项目信息
     * @return 返回项目id
     */
    @ResponseBody
    @RequestMapping(value = "/add")
    public JsonData add(ProjectParam projectParam) {
        int projectId = projectService.saveProject(projectParam);
        Map<String, Integer> resultMap = new HashMap<>(1);
        resultMap.put("projectId", projectId);
        return JsonData.success(resultMap);
    }

    /**
     * 删除项目
     * @param projectIds 项目id数组
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public JsonData delete(@RequestParam(value = "ids") Integer[] projectIds) {
        projectService.deleteProjectById(projectIds);
        return JsonData.success();
    }

    /**
     * 更新项目
     * @param projectParam 更新的项目信息
     */
    @ResponseBody
    @RequestMapping(value = "/update")
    public JsonData update(ProjectParam projectParam) {
        projectService.updateProjectById(projectParam);
        return JsonData.success();
    }
}
