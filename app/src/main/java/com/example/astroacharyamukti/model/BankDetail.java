package com.example.astroacharyamukti.model;

import com.google.gson.annotations.SerializedName;

public class BankDetail {
    @SerializedName("status")
    String status;
    @SerializedName("id")
    int id;
    @SerializedName("bfname")
    String bFName;
    @SerializedName("bank_name")
    String bank_name;
    @SerializedName("account_no")
    String account_no;
    @SerializedName("account_type")
    String account_type;
    @SerializedName("ifsc")
    String ifsc;
    @SerializedName("branch_bame")
    String branch_bName;
    @SerializedName("bank_bddress")
    String bank_bAddress;
    @SerializedName("pan")
    String pan;
    @SerializedName("gst")
    String gst;

    public BankDetail(String status, int id, String bFName, String bank_name, String account_no, String account_type, String ifsc, String branch_bName, String bank_bAddress, String pan, String gst) {
        this.status = status;
        this.id = id;
        this.bFName = bFName;
        this.bank_name = bank_name;
        this.account_no = account_no;
        this.account_type = account_type;
        this.ifsc = ifsc;
        this.branch_bName = branch_bName;
        this.bank_bAddress = bank_bAddress;
        this.pan = pan;
        this.gst = gst;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getbFName() {
        return bFName;
    }

    public void setbFName(String bFName) {
        this.bFName = bFName;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getBranch_bName() {
        return branch_bName;
    }

    public void setBranch_bName(String branch_bName) {
        this.branch_bName = branch_bName;
    }

    public String getBank_bAddress() {
        return bank_bAddress;
    }

    public void setBank_bAddress(String bank_bAddress) {
        this.bank_bAddress = bank_bAddress;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }
}
