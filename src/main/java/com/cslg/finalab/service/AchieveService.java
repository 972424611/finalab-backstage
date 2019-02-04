package com.cslg.finalab.service;

import com.cslg.finalab.param.AchieveParam;
import com.cslg.finalab.vo.AchieveVo;

import java.util.List;

public interface AchieveService {

    List<AchieveVo> getAllAchieveList();

    void saveAchieve(AchieveParam achieveParam);

    void deleteAchieveById(Integer[] achieveIds);
}
