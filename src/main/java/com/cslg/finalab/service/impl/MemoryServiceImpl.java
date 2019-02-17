package com.cslg.finalab.service.impl;

import com.cslg.finalab.dao.SysMemoryMapper;
import com.cslg.finalab.model.SysMemory;
import com.cslg.finalab.service.MemoryService;
import com.cslg.finalab.vo.MemoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public Map<Integer, Map<String, List<String>>> getAllMemoryList() {
        List<SysMemory> sysMemoryList = sysMemoryMapper.selectAll();
        Map<Integer, Map<String, List<String>>> map = new HashMap<>();
        for(SysMemory sysMemory : sysMemoryList) {
            Date date = sysMemory.getTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            if(!map.containsKey(year)) {
                map.put(year, new HashMap<>());
                Map<String, List<String>> remarkMap = new HashMap<>();
                remarkMap.put(sysMemory.getRemark(), new ArrayList<>());
                remarkMap.get(sysMemory.getRemark()).add(serverAddress + sysMemory.getPhoto());
                map.put(year, remarkMap);
            } else {
                String remark = sysMemory.getRemark();
                Map<String, List<String>> remarkMap =  map.get(year);
                if(!remarkMap.containsKey(remark)) {
                    remarkMap.put(remark, new ArrayList<>());
                }
                remarkMap.get(remark).add(serverAddress + sysMemory.getPhoto());
                map.put(year, remarkMap);
            }
        }
        return map;
    }

    @Override
    public void deleteMemoryById(Integer[] memoryIds) {
        if(memoryIds == null || memoryIds.length == 0) {
            return;
        }
        sysMemoryMapper.batchDeleteByPrimaryKey(memoryIds);
    }
}
