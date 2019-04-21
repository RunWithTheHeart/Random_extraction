package com.wyy.controller;

import com.wyy.bean.*;
import com.wyy.service.ExpertService;
import com.wyy.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
public class ExpertController extends BaseController {

    @Autowired
    private ExpertService expertService;


    /**
     * 添加专家
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping("/add_expert")
    @ResponseBody
    public Map add_expert(HttpServletRequest request, Expert expert) throws ParseException {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String created = sdf.format(date);
        expert.setCreated(sdf.parse(created));//添加专家时间

        //添加专家，默认专家账号
        //System.out.println("flag:::::"+flag);
        String message = expertService.insertExpert(expert) == 0?"添加失败":"添加成功";

        Map map = new HashMap<>();
        map.put("message",message);
        return map;
    }

    /**
     * 编辑专家
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping("/update_expert")
    @ResponseBody
    public Map update_expert(HttpServletRequest request,Expert expert) throws ParseException {

//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String created = sdf.format(date);
//        expert.setCreated(sdf.parse(created));//添加专家时间

        String message = expertService.updateExpert(expert) == 0?"编辑失败":"编辑成功";

        Map map = new HashMap<>();
        map.put("message",message);
        return map;
    }


    /**
     *
     *查询所有专家
     * @param request
     * @param response
     */
    @RequestMapping("/show_expert")
    @ResponseBody
    public PageInfo<Expert> show_expert(HttpServletRequest request, HttpServletResponse response) {

        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));
        int draw = Integer.parseInt(request.getParameter("draw"));
        System.out.println("执行了"+draw+";start:"+start+";+length:"+length);

        PageInfo<Expert> expert = expertService.selectAllExpert(start, length, draw);
        System.out.println(expert);
        return expert;
    }
    /**
     *删除专家
     * @param request
     * @param response
     */
    @RequestMapping("/delete_expert")
    @ResponseBody
    public Map delete_expert(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("执行了:id="+id);
        String message = expertService.deleteExpert(id) == 0?"删除失败":"删除成功";

        Map map = new HashMap<>();
        map.put("message",message);
        return map;
    }
    /**
     *批量删除专家
     * @param request
     * @param response
     */
    @RequestMapping("/delete_experts")
    @ResponseBody
    public Map delete_experts(HttpServletRequest request, HttpServletResponse response) {
        String ids = request.getParameter("ids");
        System.out.println("执行了:ids="+ids);
        String[] strid = ids.split(",");

        int[] arrids = new int[strid.length];
        for (int i = 0; i <strid.length; i++) {
            arrids[i] = Integer.parseInt(strid[i]);
            System.out.println(i+":"+arrids[i]);
        }


        String message = expertService.deleteExperts(arrids) == 0?"删除失败":"删除成功";
        Map map = new HashMap<>();
        map.put("message",message);
        return map;
    }

    /**
     *根据username查询所有评标项目(专业，姓名，电话)
     * @param request
     * @param response
     */
    @RequestMapping("/username_search_project")
    @ResponseBody
    public PageInfo<Expert> searchProject(HttpServletRequest request, HttpServletResponse response) {

        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));
        int draw = Integer.parseInt(request.getParameter("draw"));
        String username = request.getParameter("username");

        PageInfo<Expert> experts;

        if(username == ""){
            experts= expertService.selectAllExpert(start, length, draw);

        }else{
            experts = expertService.searchProject(start, length, draw,username); //模糊查询
        }
       //System.out.println(projects);
        return experts;
    }
}
