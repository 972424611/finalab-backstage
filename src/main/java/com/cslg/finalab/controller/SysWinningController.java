package com.cslg.finalab.controller;

import com.cslg.finalab.common.JsonData;
import com.cslg.finalab.service.SysWinningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/sys/winning")
public class SysWinningController {

    @Autowired
    private SysWinningService sysWinningService;

    @ResponseBody
    @RequestMapping(value = "/getAll")
    public JsonData getAll() {
        return JsonData.success(sysWinningService.getAllWinningList());
    }
}
