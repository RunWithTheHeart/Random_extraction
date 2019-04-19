package com.wyy.bean;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Save_project {
    private Integer id;
    private String title;
    private String contents;
    private String username;
    private Date extraction_time;
    private String strdate;

    public String getStrdate() {
        return strdate;
    }

    public void setStrdate(String strdate) {
        this.strdate = strdate;
    }

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


    public Date getExtraction_time() {
        return extraction_time;
    }

    public void setExtraction_time(Date extraction_time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.strdate = simpleDateFormat.format(extraction_time);
        this.extraction_time = extraction_time;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + contents + '\'' +
                ", username='" + username + '\'' +
                ", extraction_time=" + extraction_time +
                '}';
    }
}
