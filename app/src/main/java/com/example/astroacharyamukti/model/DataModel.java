package com.example.astroacharyamukti.model;

public class DataModel {
    int count;
    String mobile;
    String status;

    public DataModel(int count, String mobile, String status) {
        this.count = count;
        this.mobile = mobile;
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
