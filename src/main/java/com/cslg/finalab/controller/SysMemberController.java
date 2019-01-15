package com.cslg.finalab.controller;

import com.cslg.finalab.common.JsonData;
import com.cslg.finalab.service.SysMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/sys/member")
public class SysMemberController {

    @Autowired
    private SysMemberService sysMemberService;

    /**
     * 获取理事会成员的信息
     * @return JsonData
     */
    @ResponseBody
    @RequestMapping(value = "/getCouncil")
    public JsonData getCouncil() {
        return JsonData.success(sysMemberService.getCouncilList());
    }

    /**
     * 根据部门id，查询部门成员
     * @param departmentId 部门id
     * @return JsonData
     */
    @ResponseBody
    @RequestMapping(value = "/getMembers")
    public JsonData getMembers(@RequestParam(value = "id") String departmentId) {
        return JsonData.success(sysMemberService.getMemberListByDepartmentId(departmentId));
    }


}
