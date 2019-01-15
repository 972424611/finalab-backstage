package com.cslg.finalab.service;

import com.cslg.finalab.vo.CouncilVo;
import com.cslg.finalab.vo.MemberVo;

import java.util.List;

public interface SysMemberService {

    /**
     * 获取理事会成员
     * @return List<CouncilVo>
     */
    List<CouncilVo> getCouncilList();

    /**
     * 获取部门成员，根据部门id
     * @param departmentId 部门id
     * @return List<MemberVo>
     */
    List<MemberVo> getMemberListByDepartmentId(String departmentId);
}
