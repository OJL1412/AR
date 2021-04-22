package com.example.mdtest.Bean;

import cn.bmob.v3.BmobObject;

public class MyOrder extends BmobObject {
    private String tag;
    private String offer;
    private String describe;
    private String company;
    private String pickUpCode;
    private String phoneTail;
    private String status;
    private String address;
    private String userId;
    private String arId;
    private String come_time;
    private String customer;
    private int rush;



    public MyOrder(String tag, String offer, String describe, String company, String pickUpCode,
                   String phoneTail, String status, String address, String userId, String arId,
                   String come_time, String customer, int rush) {
        this.tag = tag;
        this.offer = offer;
        this.describe = describe;
        this.company = company;
        this.pickUpCode = pickUpCode;
        this.phoneTail = phoneTail;
        this.status = status;
        this.address = address;
        this.userId = userId;
        this.arId = arId;
        this.come_time = come_time;
        this.customer = customer;
        this.rush = rush;
    }

    public MyOrder() {

    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPickUpCode() {
        return pickUpCode;
    }

    public void setPickUpCode(String pickUpCode) {
        this.pickUpCode = pickUpCode;
    }

    public String getPhoneTail() {
        return phoneTail;
    }

    public void setPhoneTail(String phoneTail) {
        this.phoneTail = phoneTail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArId() {
        return arId;
    }

    public void setArId(String arId) {
        this.arId = arId;
    }

    public String getCome_time() {
        return come_time;
    }

    public void setCome_time(String come_time) {
        this.come_time = come_time;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getRush() {
        return rush;
    }

    public void setRush(int rush) {
        this.rush = rush;
    }
}
