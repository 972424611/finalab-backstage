package com.cslg.finalab.dao;

import com.cslg.finalab.model.SysWinning;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysWinningMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysWinning record);

    int insertSelective(SysWinning record);

    SysWinning selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysWinning record);

    int updateByPrimaryKey(SysWinning record);

    // -------------------------------

    List<SysWinning> selectAll();
}