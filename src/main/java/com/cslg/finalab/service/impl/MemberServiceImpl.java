package com.cslg.finalab.service.impl;

import com.cslg.finalab.common.BeanValidator;
import com.cslg.finalab.dao.SysCollegeMapper;
import com.cslg.finalab.dao.SysDepartmentMapper;
import com.cslg.finalab.dao.SysLevelMapper;
import com.cslg.finalab.dao.SysMemberMapper;
import com.cslg.finalab.enums.MemberEnum;
import com.cslg.finalab.exception.MemberException;
import com.cslg.finalab.model.*;
import com.cslg.finalab.param.MemberParam;
import com.cslg.finalab.service.MemberService;
import com.cslg.finalab.vo.CouncilVo;
import com.cslg.finalab.vo.MemberVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Value("${server_address}")
    private String serverAddress;

    @Autowired
    private SysMemberMapper sysMemberMapper;

    @Autowired
    private SysLevelMapper sysLevelMapper;

    @Autowired
    private SysCollegeMapper sysCollegeMapper;

    @Autowired
    private SysDepartmentMapper sysDepartmentMapper;

    @Override
    public List<CouncilVo> getCouncilList() {
        List<SysLevel> sysLevelList = sysLevelMapper.selectAll();
        List<CouncilVo> councilVoList = new ArrayList<>();
        for(SysLevel sysLevel : sysLevelList) {
            // TODO; 这里暂时只获取老师和总裁的信息
            List<SysMember> sysMemberList = sysMemberMapper.selectByLevelId(sysLevel.getId());
            for(SysMember sysMember : sysMemberList) {
                CouncilVo councilVo = new CouncilVo();
                councilVo.setName(sysMember.getName());
                councilVo.setCatchphrase(sysMember.getCatchphrase());
                councilVo.setImage(serverAddress + sysMember.getHeadPortrait());
                councilVo.setLevelName(sysLevel.getName());
                councilVoList.add(councilVo);
            }
        }
        if(councilVoList.size() > 1) {
            // 把实验室导师放中间位置(一共只有三个３)
            Collections.swap(councilVoList, 1, 2);
        }
        return councilVoList;
    }

    @Override
    public List<MemberVo> getMemberListByDepartmentId(Integer departmentId) {
        if(departmentId == null) {
            return new ArrayList<>();
        }
        List<SysMember> sysMemberList = sysMemberMapper.selectByDepartmentId(departmentId);
        List<MemberVo> memberVoList = new ArrayList<>();
        for(SysMember sysMember : sysMemberList) {
            MemberVo memberVo = new MemberVo();
            BeanUtils.copyProperties(sysMember, memberVo);
            memberVo.setGender(sysMember.getGender() == 0 ? "男" : "女");
            Integer levelId = sysMember.getLevelId();
            SysLevel sysLevel = sysLevelMapper.selectByPrimaryKey(levelId);
            memberVo.setLevelName(sysLevel.getName());
            Integer collegeId = sysMember.getCollege();
            SysCollege sysCollege = sysCollegeMapper.selectByPrimaryKey(collegeId);
            memberVo.setCollege(sysCollege.getCollegeName());
            memberVoList.add(memberVo);
        }
        return memberVoList;
    }

    private void checkMember(MemberParam memberParam) {
        BeanValidator.check(memberParam);
        if(memberParam == null) {
            throw new MemberException(MemberEnum.INFORMATION_CANNOT_BE_EMPTY);
        }
        // 检查学号是否重复
        String stuId = memberParam.getStuId();
        if(sysMemberMapper.countMemberByStuId(stuId) > 0) {
            throw new MemberException(MemberEnum.MEMBER_ALREADY_EXISTS);
        }
    }

    @Override
    public void saveMember(MemberParam memberParam) {
        // 检查参数
        checkMember(memberParam);
        SysMember sysMember = new SysMember();
        BeanUtils.copyProperties(memberParam, sysMember);
        // 查询学院
        SysCollege sysCollege = sysCollegeMapper.selectByCollegeName(memberParam.getCollege());
        sysMember.setCollege(sysCollege.getId());
        // 如果没有填写邮箱，则把邮箱设置为qq邮箱
        if (StringUtils.isBlank(memberParam.getEmail())) {
            sysMember.setEmail(memberParam.getQq() + "@qq.com");
        }
        // 查询部门
        SysDepartment department = sysDepartmentMapper.selectByDepartMentName(memberParam.getDepartment());
        sysMember.setDepartmentId(department.getId());
        // 查询成员级别
        SysLevel sysLevel = sysLevelMapper.selectByLevelName(memberParam.getLevel());
        sysMember.setLevelId(sysLevel.getId());
        // TODO 照片还未处理
        sysMemberMapper.insert(sysMember);
    }
}
