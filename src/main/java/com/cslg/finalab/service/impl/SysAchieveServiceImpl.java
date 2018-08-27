package com.cslg.finalab.service.impl;

import com.cslg.finalab.dao.SysAchieveMapper;
import com.cslg.finalab.dao.SysMemberMapper;
import com.cslg.finalab.model.SysAchieve;
import com.cslg.finalab.model.SysMember;
import com.cslg.finalab.service.SysAchieveService;
import com.cslg.finalab.vo.AchieveVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author twilight
 */
@Service
public class SysAchieveServiceImpl implements SysAchieveService {

    @Value("${server_address}")
    private String serverAddress;

    @Autowired
    private SysAchieveMapper sysAchieveMapper;

    @Autowired
    private SysMemberMapper sysMemberMapper;

    @Override
    public List<AchieveVo> getAllAchieveList() {
        List<SysAchieve> sysAchieveList = sysAchieveMapper.selectAll();
        List<AchieveVo> achieveVoList = new ArrayList<>();
        for(SysAchieve sysAchieve : sysAchieveList) {
            AchieveVo achieveVo = new AchieveVo();
            SysMember sysMember = sysMemberMapper.selectByStuId(sysAchieve.getStuId());
            BeanUtils.copyProperties(sysAchieve, achieveVo);
            achieveVo.setHeadPortrait(serverAddress + sysMember.getHeadPortrait());
            achieveVo.setName(sysMember.getName());
            achieveVoList.add(achieveVo);
        }
        // 随机排序
        if(achieveVoList.size() > 1) {
            Collections.shuffle(achieveVoList);
        }
        return achieveVoList;
    }
}
