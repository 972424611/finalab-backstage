package com.cslg.finalab.dao;

import com.cslg.finalab.model.SysProject;
import com.cslg.finalab.model.SysProjectWithBLOBs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysProjectWithBLOBs record);

    int insertSelective(SysProjectWithBLOBs record);

    SysProjectWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysProjectWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysProjectWithBLOBs record);

    int updateByPrimaryKey(SysProject record);

    // -------------------------------

    List<SysProjectWithBLOBs> selectAll();
}