package com.cslg.finalab.dao;

import com.cslg.finalab.model.SysMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMember record);

    int insertSelective(SysMember record);

    SysMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMember record);

    int updateByPrimaryKey(SysMember record);


    // -------------------------------

    SysMember selectByStuId(String stuId);

    List<SysMember> selectByLevelId(Integer id);

    List<SysMember> selectByDepartmentId(Integer departmentId);

    int countMemberByStuId(String stuId);

    void batchDeleteByPrimaryKey(@Param("memberIds") Integer[] memberIds);
}