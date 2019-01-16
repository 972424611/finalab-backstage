package com.cslg.finalab.dao;

import com.cslg.finalab.model.SysCollege;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysCollegeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysCollege record);

    int insertSelective(SysCollege record);

    SysCollege selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysCollege record);

    int updateByPrimaryKey(SysCollege record);

    // =========================

    SysCollege selectByCollegeName(String college);
}