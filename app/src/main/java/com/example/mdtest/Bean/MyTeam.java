package com.example.mdtest.Bean;

import cn.bmob.v3.BmobObject;

public class MyTeam extends BmobObject {
    private String userId;
    private String name;
    private String manager;
    private String num;
    private String level;
    private String goal;
    private String phone;
    private String email;
    private String introduce;
    private String status;

    public MyTeam()
    {

    }

    public MyTeam(String userId, String name, String manager, String num, String level, String goal, String phone, String email, String introduce, String status)
    {
        this.userId = userId;
        this.name = name;
        this.manager = manager;
        this.num = num;
        this.level = level;
        this.goal = goal;
        this.phone = phone;
        this.email = email;
        this.introduce = introduce;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
