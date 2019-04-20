package com.wyy.service.impl;

import com.wyy.bean.PageInfo;
import com.wyy.bean.Project;
import com.wyy.bean.Save_project;
import com.wyy.dao.ProjectMapper;
import com.wyy.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.beans.Transient;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private DataSourceTransactionManager transactionManager;

    //发表项目
    // @Transactional
    @Override
    public int insertProject(Project project) {
        String username = project.getUsername();
        int flag = 0; //接收返回参数
        //手动提交事务
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED); // 定义事务传播PROPAGATION_REQUIRES_NEW
        TransactionStatus status = transactionManager.getTransaction(def);

        try{
            flag = projectMapper.insertProject(project);
            if(projectMapper.selectSaveProject(username) != null){//有
                int del = projectMapper.deleteSaveProject(username);

            }
            transactionManager.commit(status);

        } catch (Exception e) {
            e.printStackTrace();
            // 回滚事务
            flag = 0;
           transactionManager.rollback(status);
        }
        return flag;
    }


    //查询所有项目
    @Override
    public PageInfo<Project> selectAllProject(int start, int length, int draw) {
        int count = projectMapper.count();
        Map<String, Object> params = new HashMap<>();
        params.put("start",start);
        params.put("length",length);

        PageInfo<Project> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(projectMapper.selectAllProject(params));
        return pageInfo;
    }

    //保存项目  0:失败 1：成功
    @Override
    public int saveProject(Project project) {
        int flag = 0;
        String username = project.getUsername();

        if( projectMapper.selectSaveProject(username) != null){//已存在
            System.out.println("项目已存在");
            flag =  projectMapper.updateSaveProject(project);
        }else{ //不存在
            flag = projectMapper.saveProject(project);
            System.out.println("项目已存在");
        }
        return flag;
    }

    //查询保存的项目
    @Override
    public Save_project selectSaveProject(String username) {
        return projectMapper.selectSaveProject(username);
    }

    //删除一个项目
    @Override
    public int deleteProject(int id) {
        return projectMapper.deleteProject(id);
    }
    //批量删除
    @Override
    public int deleteProjects(int ids[]) {
        return projectMapper.deleteProjects(ids);
    }
    //username条件查询
    @Override
    public PageInfo<Project> searchProject(int start, int length, int draw, String username) {
        int count = projectMapper.counts(username);
        Map<String, Object> params = new HashMap<>();
        params.put("start",start);
        params.put("length",length);
        params.put("username",username);

        PageInfo<Project> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(projectMapper.searchProject(params));
        return pageInfo;
    }
}
