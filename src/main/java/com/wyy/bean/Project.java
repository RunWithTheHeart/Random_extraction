package com.wyy.bean;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Project {
    private Integer id;
    private String title;
    private String contents;
    private String username;
    private Date issuing_time;
    private Date extraction_time;
    private String str_issuing_time;  //转换日期
    private String str_extraction_time;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getIssuing_time() {
        return issuing_time;
    }

    public void setIssuing_time(Date issuing_time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.str_issuing_time = sdf.format(issuing_time);
        this.issuing_time = issuing_time;
    }

    public Date getExtraction_time() {
        return extraction_time;
    }

    public void setExtraction_time(Date extraction_time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.str_extraction_time = sdf.format(extraction_time);
        this.extraction_time = extraction_time;
    }

    public String getStr_issuing_time() {
        return str_issuing_time;
    }

    public void setStr_issuing_time(String str_issuing_time) {
        this.str_issuing_time = str_issuing_time;
    }

    public String getStr_extraction_time() {
        return str_extraction_time;
    }

    public void setStr_extraction_time(String str_extraction_time) {
        this.str_extraction_time = str_extraction_time;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", username='" + username + '\'' +
                ", issuing_time=" + issuing_time +
                ", extraction_time=" + extraction_time +
                '}';
    }
}
