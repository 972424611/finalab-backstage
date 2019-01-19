package com.cslg.finalab.service;

import com.cslg.finalab.beans.PageQuery;
import com.cslg.finalab.beans.PageResult;
import com.cslg.finalab.param.MemberParam;
import com.cslg.finalab.vo.CouncilVo;
import com.cslg.finalab.vo.MemberVo;

import java.util.List;

public interface MemberService {

    /**
     * 获取理事会成员
     * @return List<CouncilVo>
     */
    List<CouncilVo> getCouncilList();

    /**
     * 获取部门成员，根据部门id。
     * @param departmentId 部门id
     * @param pageQuery 分页
     * @return PageResult<MemberVo>
     */
    PageResult<MemberVo> getMemberListByDepartmentId(Integer departmentId, PageQuery pageQuery);

    /**
     * 获取所有成员
     * @param pageQuery 分页
     * @return PageResult<MemberVo>
     */
    PageResult<MemberVo> getAllMemberList(PageQuery pageQuery);

    /**
     * 保存成员
     * @param memberParam 请求body
     */
    void saveMember(MemberParam memberParam);

    /**
     * 根据成员id，删除成员
     * @param memberIds 成员ids
     */
    void deleteMemberById(Integer[] memberIds);

    /**
     * 更新成员，只更新不为null的属性。
     * @param memberParam 对象中只包含用户改变的属性
     */
    void updateMemberById(MemberParam memberParam);

}
