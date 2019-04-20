package com.wyy.dao;


import com.wyy.bean.Project;
import com.wyy.bean.Save_project;
import com.wyy.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProjectMapper {

    //发布一条（插入）
    int insertProject(Project project);
    //查询所有评标项目
    List<Project>  selectAllProject(Map<String,Object> params);
    //查询数据总条数
    int count();
    //保存一条项目
    int  saveProject(Project project);
    //根据username查询
    Save_project selectSaveProject(String username);
    //根据username删除
    int  deleteSaveProject(String username);
    //根据username更新
    int updateSaveProject(Project project);
    //根据id删除一个项目
    int deleteProject(int id);
    //根据id删除多个项目
    int deleteProjects(int ids[]);
    //根据username查询数据总条数
    int counts(String username);
    //根据username查询所有评标项目  start,length,username
    List<Project> searchProject(Map<String, Object> params);
}