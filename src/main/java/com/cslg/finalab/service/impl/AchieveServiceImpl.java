package com.cslg.finalab.service.impl;

import com.cslg.finalab.common.BeanValidator;
import com.cslg.finalab.dao.SysAchieveMapper;
import com.cslg.finalab.dao.SysMemberMapper;
import com.cslg.finalab.enums.AchieveEnum;
import com.cslg.finalab.exception.AchieveException;
import com.cslg.finalab.model.SysAchieve;
import com.cslg.finalab.model.SysMember;
import com.cslg.finalab.param.AchieveParam;
import com.cslg.finalab.service.AchieveService;
import com.cslg.finalab.vo.AchieveVo;
import org.apache.commons.lang3.StringUtils;
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
public class AchieveServiceImpl implements AchieveService {

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

    @Override
    public void saveAchieve(AchieveParam achieveParam) {
        // 检查参数
        BeanValidator.check(achieveParam);
        // 判断该学号对应的成员是否已经添加
        if(sysAchieveMapper.countAchieveByStuId(achieveParam.getStuId()) > 0) {
            throw new AchieveException(AchieveEnum.ACHIEVE_ALREADY_EXISTS);
        }
        SysAchieve sysAchieve = new SysAchieve();
        BeanUtils.copyProperties(achieveParam, sysAchieve);
        // 如果传过来的标语为null，则看看sys_member表中该学号对应的学生信息是否有标语。
        if(StringUtils.isBlank(sysAchieve.getCatchphrase())) {
            SysMember sysMember = sysMemberMapper.selectByStuId(sysAchieve.getStuId());
            if(StringUtils.isNotBlank(sysMember.getCatchphrase())) {
                sysAchieve.setCatchphrase(sysMember.getCatchphrase());
            }
        }
        sysAchieveMapper.insert(sysAchieve);
    }

    @Override
    public void deleteAchieveById(Integer[] achieveIds) {
        if(achieveIds == null || achieveIds.length == 0) {
            return;
        }
        sysAchieveMapper.batchDeleteByPrimaryKey(achieveIds);
    }


}
