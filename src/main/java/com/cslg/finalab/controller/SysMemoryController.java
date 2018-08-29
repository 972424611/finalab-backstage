package com.cslg.finalab.controller;

import com.cslg.finalab.common.JsonData;
import com.cslg.finalab.service.SysMemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/sys/memory")
public class SysMemoryController {

    @Autowired
    private SysMemoryService sysMemoryService;
    @ResponseBody
    @RequestMapping(value = "/random")
    public JsonData random() {
        return JsonData.success(sysMemoryService.getMemoryByRandom());
    }
}
