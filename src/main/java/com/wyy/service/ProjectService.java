package com.wyy.service;

import com.wyy.bean.PageInfo;
import com.wyy.bean.Project;
import com.wyy.bean.Save_project;
import org.springframework.stereotype.Service;

@Service
public interface ProjectService {
    //插入数据
    int insertProject(Project project);
    //查询所有项目
    PageInfo<Project> selectAllProject(int start, int length, int draw);


    //保存项目
    int saveProject(Project project);

    //查询保存的项目
    Save_project selectSaveProject(String username);

}
