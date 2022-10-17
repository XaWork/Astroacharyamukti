package com.example.astroacharyamukti.model;

public class BankData {
    private BankDetail bankDetail;

    public BankData(BankDetail bankDetail) {
        this.bankDetail = bankDetail;
    }

    public BankDetail getBankDetails() {
        return bankDetail;
    }

    public void setBankDetails(BankDetail bankDetail) {
        this.bankDetail = bankDetail;
    }
}
