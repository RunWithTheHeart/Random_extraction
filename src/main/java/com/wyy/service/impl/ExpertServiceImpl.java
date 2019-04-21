package com.wyy.service.impl;

import com.wyy.bean.*;
import com.wyy.dao.ExpertMapper;
import com.wyy.dao.ProjectMapper;
import com.wyy.dao.UserMapper;
import com.wyy.service.ExpertService;
import com.wyy.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExpertServiceImpl implements ExpertService {

    @Autowired
    private ExpertMapper expertMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DataSourceTransactionManager transactionManager;

    //添加专家
    // @Transactional
    @Override
    public int insertExpert(Expert expert) {
        String identity = expert.getIdentity(); //身份证
        int flag = 0; //接收返回参数
        //手动提交事务
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED); // 定义事务传播PROPAGATION_REQUIRES_NEW
        TransactionStatus status = transactionManager.getTransaction(def);


        if(expertMapper.selectByIdentity(identity)!= null){//有
            return  flag; //专家已存在
        }else{
            try{
                flag = expertMapper.insertExpert(expert);
                //专家默认账号
                User user = new User();
                user.setUsername(expert.getIdentity());
                user.setPassword("123456");
                user.setEmail(expert.getEmail());

                flag =  userMapper.insertUser(user);   //添加用户

                transactionManager.commit(status);
            } catch (Exception e) {
                e.printStackTrace();
                // 回滚事务
                flag = 0;
                transactionManager.rollback(status);
            }

        }
        return flag;
    }

    //更新专家根据id
    @Override
    public int updateExpert(Expert expert) {
        return expertMapper.updateExpert(expert);
    }

    //查询所有专家
    @Override
    public PageInfo<Expert> selectAllExpert(int start, int length, int draw) {
        int count = expertMapper.count();

        Map<String, Object> params = new HashMap<>();
        params.put("start",start);
        params.put("length",length);

        PageInfo<Expert> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(expertMapper.selectAllExpert(params));

        return pageInfo;
    }


    //删除一个专家
    @Override
    public int deleteExpert(int id) {
        return expertMapper.deleteExpert(id);
    }
    //批量删除专家
    @Override
    public int deleteExperts(int ids[]) {
        return expertMapper.deleteExpert(ids);
    }






    @Override
    public PageInfo<Expert> searchProject(int start, int length, int draw, String username) {
        return null;
    }
    //username条件查询
  /*  @Override
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
    }*/
}
