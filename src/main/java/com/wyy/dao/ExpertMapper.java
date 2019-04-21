package com.wyy.dao;


import com.wyy.bean.Expert;
import com.wyy.bean.Project;
import com.wyy.bean.Save_project;

import java.util.List;
import java.util.Map;

public interface ExpertMapper {

    //添加专家（插入）
    int insertExpert(Expert expert);

    //更新专家信息
    int updateExpert(Expert expert);

    //查询所有专家
    List<Expert>  selectAllExpert(Map<String, Object> params);

    //查询数据总条数
    int count();

    //根据id删除一个专家
    int deleteExpert(int id);

    //根据id删除多个专家
    int deleteExpert(int ids[]);

    //根据条件查询数据总条数
    int counts(String username);

    //根据username查询所有评标项目  start,length,username
    List<Expert> searchExpert(Map<String, Object> params);

    //根据身份证查询专家是否存在
    Expert selectByIdentity(String identity);
}