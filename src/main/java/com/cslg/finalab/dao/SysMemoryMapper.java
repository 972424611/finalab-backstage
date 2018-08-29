package com.cslg.finalab.dao;

import com.cslg.finalab.model.SysMemory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMemoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMemory record);

    int insertSelective(SysMemory record);

    SysMemory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMemory record);

    int updateByPrimaryKey(SysMemory record);

    // -------------------------------

    List<SysMemory> selectAll();
}