package com.cslg.finalab.dao;

import com.cslg.finalab.model.SysDepartment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysDepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDepartment record);

    int insertSelective(SysDepartment record);

    SysDepartment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDepartment record);

    int updateByPrimaryKey(SysDepartment record);
}