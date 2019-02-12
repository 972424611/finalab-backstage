package com.cslg.finalab.service.impl;

import com.cslg.finalab.common.BeanValidator;
import com.cslg.finalab.dao.SysWinningMapper;
import com.cslg.finalab.enums.WinningEnum;
import com.cslg.finalab.exception.WinningException;
import com.cslg.finalab.model.SysWinning;
import com.cslg.finalab.param.WinningParam;
import com.cslg.finalab.service.WinningService;
import com.cslg.finalab.vo.WinningVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WinningServiceImpl implements WinningService {

    @Value("${server_address}")
    private String serverAddress;

    @Autowired
    private SysWinningMapper sysWinningMapper;

    @Override
    public List<WinningVo> getAllWinningList() {
        List<SysWinning> sysWinningList = sysWinningMapper.selectAll();
        List<WinningVo> winningVoList = new ArrayList<>();
        for(SysWinning sysWinning : sysWinningList) {
            WinningVo winningVo = new WinningVo();
            BeanUtils.copyProperties(sysWinning, winningVo, "award_image");
            winningVo.setAwardImage(serverAddress + sysWinning.getAwardImage());
            winningVo.setProfile(sysWinning.getMembers());
            winningVoList.add(winningVo);
        }
        // 随机排序
        if(winningVoList.size() > 1) {
            Collections.shuffle(winningVoList);
        }
        return winningVoList;
    }

    @Override
    public int saveWinning(WinningParam winningParam) {
        BeanValidator.check(winningParam);
        SysWinning sysWinning = new SysWinning();
        BeanUtils.copyProperties(winningParam, sysWinning);
        sysWinningMapper.insertSelectiveAndGetWinningId(sysWinning);
        return sysWinning.getId();
    }

    @Override
    public void deleteWinningById(Integer[] winningIds) {
        if(winningIds == null || winningIds.length == 0) {
            return;
        }
        sysWinningMapper.batchDeleteByPrimaryKey(winningIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateWinningById(WinningParam winningParam) {
        SysWinning sysWinning = new SysWinning();
        BeanUtils.copyProperties(winningParam, sysWinning);
        sysWinningMapper.updateByPrimaryKeySelective(sysWinning);
    }
}
