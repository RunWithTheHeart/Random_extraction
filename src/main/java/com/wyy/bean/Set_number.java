package com.wyy.bean;

public class Set_number {
    private Integer id;
    private Integer project_id;
    private String profession;
    private  int total; //总人数
    private  int num;   //抽取人数

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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Set_number{" +
                "id=" + id +
                ", project_id=" + project_id +
                ", profession='" + profession + '\'' +
                ", total=" + total +
                ", num=" + num +
                '}';
    }
}
