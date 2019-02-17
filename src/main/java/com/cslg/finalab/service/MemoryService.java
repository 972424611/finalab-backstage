package com.cslg.finalab.service;

import com.cslg.finalab.vo.MemoryVo;

import java.util.List;
import java.util.Map;

public interface MemoryService {

    /**
     * 随机获取团队活动记忆照片
     * @return 团队活动记忆照片数组
     */
    List<MemoryVo> getMemoryByRandom();

    /**
     * 获取所有团队活动照片
     * @return 树形的信息
     */
    Map<Integer, Map<String, List<String>>> getAllMemoryList();

    /**
     * 通过id删除团队活动照片
     * @param memoryIds
     */
    void deleteMemoryById(Integer[] memoryIds);
}
