package com.cslg.finalab.controller;

import com.cslg.finalab.beans.JsonData;
import com.cslg.finalab.service.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @ResponseBody
    @RequestMapping(value = "/getAll")
    public JsonData getAll() {
        Map<Integer, Map<String, List<String>>> allMemoryList = memoryService.getAllMemoryList();
        return JsonData.success(allMemoryList);
    }
}
