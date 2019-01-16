package com.cslg.finalab.service.impl;

import com.cslg.finalab.dao.SysMemoryMapper;
import com.cslg.finalab.model.SysMemory;
import com.cslg.finalab.service.MemoryService;
import com.cslg.finalab.vo.MemoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MemoryServiceImpl implements MemoryService {

    @Value("${server_address}")
    private String serverAddress;

    @Autowired
    private SysMemoryMapper sysMemoryMapper;

    @Override
    public List<MemoryVo> getMemoryByRandom() {
        List<SysMemory> sysMemoryList = sysMemoryMapper.selectAll();
        // 随机排序
        if(sysMemoryList.size() > 1) {
            Collections.shuffle(sysMemoryList);
        }
        List<MemoryVo> memoryVoList = new ArrayList<>();
        for(SysMemory sysMemory : sysMemoryList) {
            MemoryVo memoryVo = new MemoryVo();
            BeanUtils.copyProperties(sysMemory, memoryVo);
            memoryVo.setPhoto(serverAddress + sysMemory.getPhoto());
            memoryVoList.add(memoryVo);
            // 每次只展示11张图片
            if(memoryVoList.size() >= 10) {
                break;
            }
        }
        return memoryVoList;
    }
}
