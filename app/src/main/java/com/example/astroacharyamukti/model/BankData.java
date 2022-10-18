package com.example.astroacharyamukti.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BankData {
    @SerializedName("Bank_Details")
    private List<BankDetail> bankDetail=null;

    public BankData(List<BankDetail> bankDetail) {
        this.bankDetail = bankDetail;
    }

    public List<BankDetail> getBankDetail() {
        return bankDetail;
    }

    public void setBankDetail(List<BankDetail> bankDetail) {
        this.bankDetail = bankDetail;
    }
}
