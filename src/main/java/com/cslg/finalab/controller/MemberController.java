package com.cslg.finalab.controller;

import com.cslg.finalab.common.JsonData;
import com.cslg.finalab.param.MemberParam;
import com.cslg.finalab.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/sys/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 获取理事会成员的信息
     * @return JsonData
     */
    @ResponseBody
    @RequestMapping(value = "/getCouncil")
    public JsonData getCouncil() {
        return JsonData.success(memberService.getCouncilList());
    }

    /**
     * 根据部门id，查询部门成员
     * @param departmentId 部门id
     * @return JsonData
     */
    @ResponseBody
    @RequestMapping(value = "/getMembers")
    public JsonData getMembers(@RequestParam(value = "id") Integer departmentId) {
        return JsonData.success(memberService.getMemberListByDepartmentId(departmentId));
    }

    @ResponseBody
    @RequestMapping(value = "/addMember")
    public JsonData addMember(MemberParam memberParam) {
        memberService.saveMember(memberParam);
        return JsonData.success();
    }
}
