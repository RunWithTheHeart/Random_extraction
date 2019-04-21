package com.wyy.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Expert {
    private Integer id;
    private String expert_number;
    private String name;
    private String gender;
    private Date birthday;
    private String strbirthday;//显示时间
    private String job_title;
    private String level;
    private String profession;
    private String identity;
    private String mobile;
    private String email;
    private String address;
    private Date created;
    private String strcreated;//显示时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpert_number() {
        return expert_number;
    }

    public void setExpert_number(String expert_number) {
        this.expert_number = expert_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.strbirthday = sdf.format(birthday);
        this.birthday = birthday;
    }

    public String getStrbirthday() {
        return strbirthday;
    }

    public void setStrbirthday(String strbirthday) {
        this.strbirthday = strbirthday;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.strcreated = sdf.format(created);
        this.created = created;
    }

    public String getStrcreated() {
        return strcreated;
    }

    public void setStrcreated(String strcreated) {
        this.strcreated = strcreated;
    }

    @Override
    public String toString() {
        return "Expert{" +
                "id=" + id +
                ", expert_number='" + expert_number + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", strbirthday='" + strbirthday + '\'' +
                ", job_title='" + job_title + '\'' +
                ", level='" + level + '\'' +
                ", profession='" + profession + '\'' +
                ", identity='" + identity + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", created=" + created +
                ", strcreated='" + strcreated + '\'' +
                '}';
    }
}
