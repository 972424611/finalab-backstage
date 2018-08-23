package com.cslg.finalab.dao;

import com.cslg.finalab.model.SysAchieve;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysAchieveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysAchieve record);

    int insertSelective(SysAchieve record);

    SysAchieve selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAchieve record);

    int updateByPrimaryKey(SysAchieve record);

    // -----------------------------

    List<SysAchieve> selectAll();
}