package com.wyy.service;

import com.wyy.bean.Expert;
import com.wyy.bean.PageInfo;
import com.wyy.bean.Project;
import com.wyy.bean.Save_project;
import org.springframework.stereotype.Service;

@Service
public interface ExpertService {

    //插入专家
    int insertExpert(Expert expert);

    //更新专家信息
    int updateExpert(Expert expert);

    //查询所有专家
    PageInfo<Expert> selectAllExpert(int start, int length, int draw);

    //根据id删除专家
    int deleteExpert(int id);

    //根据id批量删除专家
    int deleteExperts(int[] arrids);

    //条件查询专家
    PageInfo<Expert> searchProject(int start, int length, int draw, String username);
}
