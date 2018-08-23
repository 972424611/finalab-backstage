package com.cslg.finalab.dao;

import com.cslg.finalab.model.SysLevel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysLevel record);

    int insertSelective(SysLevel record);

    SysLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLevel record);

    int updateByPrimaryKey(SysLevel record);

    // -------------------------------

    List<SysLevel> selectAll();
}