package com.cslg.finalab.service;

import com.cslg.finalab.vo.MemoryVo;

import java.util.List;

public interface MemoryService {

    /**
     * 随机获取团队活动记忆照片
     * @return 团队活动记忆照片数组
     */
    List<MemoryVo> getMemoryByRandom();
}
