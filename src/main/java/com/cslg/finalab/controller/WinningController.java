package com.cslg.finalab.controller;

import com.cslg.finalab.beans.JsonData;
import com.cslg.finalab.param.WinningParam;
import com.cslg.finalab.service.WinningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/sys/winning")
public class WinningController {

    @Autowired
    private WinningService winningService;

    /**
     * 获取所有获奖信息
     */
    @ResponseBody
    @RequestMapping(value = "/getAll")
    public JsonData getAll() {
        return JsonData.success(winningService.getAllWinningList());
    }

    /**
     * 新增获奖信息
     * @param winningParam 获奖信息
     */
    @ResponseBody
    @RequestMapping(value = "/add")
    public JsonData add(WinningParam winningParam) {
        int winningId = winningService.saveWinning(winningParam);
        Map<String, Integer> resultMap = new HashMap<>(1);
        resultMap.put("winningId", winningId);
        return JsonData.success(resultMap);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public JsonData delete(@RequestParam(value = "ids") Integer[] winningIds) {
        winningService.deleteWinningById(winningIds);
        return JsonData.success();
    }
}
