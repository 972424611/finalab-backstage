package com.cslg.finalab.controller;

import com.cslg.finalab.common.JsonData;
import com.cslg.finalab.service.SysMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/sys/member")
public class SysMemberController {

    @Autowired
    private SysMemberService sysMemberService;

    @ResponseBody
    @RequestMapping(value = "/getCouncil")
    public JsonData getCouncil() {
        return JsonData.success(sysMemberService.getCouncilList());
    }
}
