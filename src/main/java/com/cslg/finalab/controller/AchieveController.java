package com.cslg.finalab.controller;

import com.cslg.finalab.beans.JsonData;
import com.cslg.finalab.param.AchieveParam;
import com.cslg.finalab.service.AchieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/sys/achieve")
public class AchieveController {

    @Autowired
    private AchieveService achieveService;

    /**
     * 获取所有成员去向
     * @return JsonData
     */
    @ResponseBody
    @RequestMapping(value = "/getAll")
    public JsonData getAll() {
        return JsonData.success(achieveService.getAllAchieveList());
    }

    /**
     * 增加成员去向
     * @param achieveParam 请求body数据
     * @return JsonData
     */
    @ResponseBody
    @RequestMapping(value = "/addAchieve")
    public JsonData addAchieve(AchieveParam achieveParam) {
        achieveService.saveAchieve(achieveParam);
        return JsonData.success();
    }

    /**
     * 根据id，删除成员去向
     * @param achieveIds 成员去向
     * @return JsonData
     */
    @ResponseBody
    @RequestMapping(value = "/deleteAchieve")
    public JsonData deleteAchieve(@RequestParam(value = "ids") Integer[] achieveIds) {
        achieveService.deleteAchieveById(achieveIds);
        return JsonData.success();
    }


}
