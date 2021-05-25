package com.example.mdtest.Bean;

import cn.bmob.v3.BmobObject;

public class MyAddress extends BmobObject {

    private String userId;
    private String selfName;
    private String province;
    private String city;
    private String region;
    private String school;
    private String building;
    private String detailedAdd;

    public MyAddress(String userId, String selfName, String province, String city, String region, String school, String building, String detailedAdd)
    {
        this.userId = userId;
        this.selfName = selfName;
        this.province = province;
        this.city = city;
        this.region = region;
        this.school = school;
        this.building = building;
        this.detailedAdd = detailedAdd;
    }

    public MyAddress()
    {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSelfName() {
        return selfName;
    }

    public void setSelfName(String selfName) {
        selfName = selfName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getDetailedAdd() {
        return detailedAdd;
    }

    public void setDetailedAdd(String detailedAdd) {
        this.detailedAdd = detailedAdd;
    }
}
