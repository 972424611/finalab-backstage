package com.cslg.finalab.controller;

import com.cslg.finalab.beans.JsonData;
import com.cslg.finalab.service.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/sys/memory")
public class MemoryController {

    @Autowired
    private MemoryService memoryService;

    /**
     * 随机获取照片
     */
    @ResponseBody
    @RequestMapping(value = "/random")
    public JsonData random() {
        return JsonData.success(memoryService.getMemoryByRandom());
    }

    /**
     * 获取团队照片，用于后台树形目录的展示
     */
    @ResponseBody
    @RequestMapping(value = "/getAll")
    public JsonData getAll() {
        Map<Integer, Map<String, List<String>>> allMemoryList = memoryService.getAllMemoryList();
        return JsonData.success(allMemoryList);
    }

    /**
     * 删除团队照片
     * @param memoryIds 团队照片id数组
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public JsonData delete(@RequestParam(value = "ids") Integer[] memoryIds) {
        memoryService.deleteMemoryById(memoryIds);
        return JsonData.success();
    }

}
