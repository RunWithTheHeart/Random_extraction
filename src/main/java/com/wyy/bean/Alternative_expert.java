package com.wyy.bean;

import java.util.Date;

public class Alternative_expert {
    private Integer id;
    private Integer project_id;
    private Integer expert_id;
    private String profession;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public Integer getExpert_id() {
        return expert_id;
    }

    public void setExpert_id(Integer expert_id) {
        this.expert_id = expert_id;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "Alternative_expert{" +
                "id=" + id +
                ", project_id=" + project_id +
                ", expert_id=" + expert_id +
                ", profession='" + profession + '\'' +
                '}';
    }
}
