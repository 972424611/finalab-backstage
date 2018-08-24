package com.cslg.finalab.dao;

import com.cslg.finalab.model.SysProject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysProject record);

    int insertSelective(SysProject record);

    SysProject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysProject record);

    int updateByPrimaryKey(SysProject record);

    // -------------------------------

    List<SysProject> selectAll();
}