package com.cslg.finalab.controller;

import com.cslg.finalab.common.JsonData;
import com.cslg.finalab.service.WinningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/sys/winning")
public class WinningController {

    @Autowired
    private WinningService winningService;

    @ResponseBody
    @RequestMapping(value = "/getAll")
    public JsonData getAll() {
        return JsonData.success(winningService.getAllWinningList());
    }
}
