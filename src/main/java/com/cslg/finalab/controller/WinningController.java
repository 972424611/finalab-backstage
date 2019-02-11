package com.cslg.finalab.controller;

import com.cslg.finalab.beans.JsonData;
import com.cslg.finalab.param.WinningParam;
import com.cslg.finalab.service.WinningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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

    @ResponseBody
    @RequestMapping(value = "/add")
    public JsonData add(WinningParam winningParam) {
        int winningId = winningService.saveWinning(winningParam);
        Map<String, Integer> resultMap = new HashMap<>(1);
        resultMap.put("winningId", winningId);
        return JsonData.success(resultMap);
    }
}
