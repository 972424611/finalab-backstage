package com.cslg.finalab.service.impl;

import com.cslg.finalab.dao.SysLevelMapper;
import com.cslg.finalab.dao.SysMemberMapper;
import com.cslg.finalab.model.SysLevel;
import com.cslg.finalab.model.SysMember;
import com.cslg.finalab.service.SysMemberService;
import com.cslg.finalab.vo.CouncilVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SysMemberServiceImpl implements SysMemberService {

    @Value("${server_address}")
    private String serverAddress;

    @Autowired
    private SysMemberMapper sysMemberMapper;

    @Autowired
    private SysLevelMapper sysLevelMapper;

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
        //把实验室导师放中间位置(一共只有三个３)
        Collections.swap(councilVoList, 1, 2);
        return councilVoList;
    }
}
