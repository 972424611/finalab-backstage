package com.cslg.finalab.service.impl;

import com.cslg.finalab.dao.SysWinningMapper;
import com.cslg.finalab.model.SysWinning;
import com.cslg.finalab.service.SysWinningService;
import com.cslg.finalab.vo.WinningVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysWinningServiceImpl implements SysWinningService {

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
        return winningVoList;
    }
}
