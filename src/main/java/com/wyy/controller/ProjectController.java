package com.wyy.controller;

import com.wyy.bean.Admin;
import com.wyy.bean.PageInfo;
import com.wyy.bean.Project;
import com.wyy.bean.Save_project;
import com.wyy.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;


    /**
     * 发布评标项目
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping("/submit_project")
    @ResponseBody
    public Map insert_project(HttpServletRequest request) throws ParseException {
        Project project = new Project();
        project.setTitle(request.getParameter("title"));
        project.setContents(request.getParameter("contents"));
        project.setUsername(request.getParameter("username"));


        String extraction_time = request.getParameter("extraction_time");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        project.setExtraction_time(sdf.parse(extraction_time));//抽取时间

        Date date = new Date();
        String dateStr = sdf.format(date);
        project.setIssuing_time(sdf.parse(dateStr));//发布时间issuing_time
        int flag = projectService.insertProject(project) ;
        System.out.println("flag:::::"+flag);
        String message = projectService.insertProject(project) == 0?"提交失败":"提交成功";

        Map map = new HashMap<>();
        map.put("message",message);
        return map;
    }

    /**
     * 保存项目
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping("/save_project")
    @ResponseBody
    public Map save_project(HttpServletRequest request) throws ParseException {
        Project project = new Project();
        project.setTitle(request.getParameter("title"));
        project.setContents(request.getParameter("contents"));
        project.setUsername(request.getParameter("username"));

        String extraction_time = request.getParameter("extraction_time");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        project.setExtraction_time(sdf.parse(extraction_time));//抽取时间



        String message = projectService.saveProject(project) == 0?"保存失败":"保存成功";

        Map map = new HashMap<>();
        map.put("message",message);
        return map;
    }
    /**
     * 查询保存项目
     * @param session
     * @return
     * @throws ParseException
     */
    @RequestMapping("/select_save_project")
    @ResponseBody
    public Save_project select_save_project(HttpSession session) {
        Admin admin =(Admin) session.getAttribute("admin");
        Save_project project = projectService.selectSaveProject(admin.getUsername());
        System.out.println(project);
        return project;
    }






    /**
     *
     *查询所有项目
     * @param request
     * @param response
     */
    @RequestMapping("/show_project")
    @ResponseBody
    public PageInfo<Project> showUser(HttpServletRequest request, HttpServletResponse response) {

        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));
        int draw = Integer.parseInt(request.getParameter("draw"));
        System.out.println("执行了"+draw+";start:"+start+";+length:"+length);
        PageInfo<Project> projects = projectService.selectAllProject(start, length, draw);
        System.out.println(projects);
        return projects;
    }

}
