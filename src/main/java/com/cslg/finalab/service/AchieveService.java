package com.cslg.finalab.service;

import com.cslg.finalab.param.AchieveParam;
import com.cslg.finalab.vo.AchieveVo;

import java.util.List;

public interface AchieveService {

    /**
     * 获取所有成员去想
     * @return 成员去向信息数组
     */
    List<AchieveVo> getAllAchieveList();

    /**
     * 保存成员去向
     * @param achieveParam 成员去向信息
     */
    void saveAchieve(AchieveParam achieveParam);

    /**
     * 根据去向Id，删除成员去向
     * @param achieveIds 成员去向id
     */
    void deleteAchieveById(Integer[] achieveIds);
}
