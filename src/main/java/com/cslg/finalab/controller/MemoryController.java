package com.cslg.finalab.controller;

import com.cslg.finalab.beans.JsonData;
import com.cslg.finalab.service.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/sys/memory")
public class MemoryController {

    @Autowired
    private MemoryService memoryService;

    @ResponseBody
    @RequestMapping(value = "/random")
    public JsonData random() {
        return JsonData.success(memoryService.getMemoryByRandom());
    }
}
