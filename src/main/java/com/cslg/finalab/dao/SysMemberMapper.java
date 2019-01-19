package com.cslg.finalab.dao;

import com.cslg.finalab.beans.PageQuery;
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

    List<SysMember> selectByDepartmentId(@Param("departmentId") Integer departmentId, @Param("pageQuery") PageQuery pageQuery);

    int countMemberByStuId(String stuId);

    void batchDeleteByPrimaryKey(@Param("memberIds") Integer[] memberIds);

    int countMemberByDepartmentId(@Param("departmentId") Integer departmentId);

    int countAllMember();

    List<SysMember> selectAllMember(@Param("pageQuery") PageQuery pageQuery);

}