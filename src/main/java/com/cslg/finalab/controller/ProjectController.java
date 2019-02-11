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

    @ResponseBody
    @RequestMapping(value = "/getAll")
    public JsonData getAll() {
        return JsonData.success(projectService.getAllProjectList());
    }

    @ResponseBody
    @RequestMapping(value = "/detail")
    public JsonData detail(@RequestParam(value = "id") Integer id) {
        return JsonData.success(projectService.getProjectDetailById(id));
    }

    @ResponseBody
    @RequestMapping(value = "/add")
    public JsonData add(ProjectParam projectParam) {
        int projectId = projectService.saveProject(projectParam);
        Map<String, Integer> resultMap = new HashMap<>(1);
        resultMap.put("projectId", projectId);
        return JsonData.success(resultMap);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public JsonData delete(@RequestParam(value = "ids") Integer[] projectIds) {
        projectService.deleteProjectById(projectIds);
        return JsonData.success();
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public JsonData update(ProjectParam projectParam) {
        projectService.updateProjectById(projectParam);
        return JsonData.success();
    }
}
