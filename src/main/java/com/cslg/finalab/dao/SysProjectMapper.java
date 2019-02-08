package com.cslg.finalab.dao;

import com.cslg.finalab.model.SysProject;
import com.cslg.finalab.model.SysProjectWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    int countProjectByProjectName(String name);

    void insertAndGetProjectId(SysProjectWithBLOBs sysProjectWithBLOBs);

    void insertSelectiveAndGetProjectId(SysProjectWithBLOBs sysProjectWithBLOBs);

    void updateImagesByProjectId(@Param("images") String images, @Param("id") int projectId);

    void updateCoverImageByProjectId(@Param("coverImage") String coverImage, @Param("id") int projectId);

    int countProjectByProjectId(@Param("id") int projectId);

    void batchDeleteByPrimaryKey(@Param("projectIds") Integer[] projectIds);
}