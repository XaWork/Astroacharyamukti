package com.example.astroacharyamukti.model;

public class EarnDetails {
    String consultation_id;
    String nqme;
    String consultation_type;
    String earamount;
    String amount;
    String duration;
    String date;

    public String getNqme() {
        return nqme;
    }

    public void setNqme(String nqme) {
        this.nqme = nqme;
    }

    public EarnDetails(String consultation_id, String nqme, String consultation_type, String earamount, String amount, String duration, String date) {
        this.consultation_id = consultation_id;
        this.nqme=nqme;
        this.consultation_type = consultation_type;
        this.earamount = earamount;
        this.amount = amount;
        this.duration = duration;
        this.date = date;
    }

    public String getConsultation_id() {
        return consultation_id;
    }

    public void setConsultation_id(String consultation_id) {
        this.consultation_id = consultation_id;
    }

    public String getConsultation_type() {
        return consultation_type;
    }

    public void setConsultation_type(String consultation_type) {
        this.consultation_type = consultation_type;
    }

    public String getEaramount() {
        return earamount;
    }

    public void setEaramount(String earamount) {
        this.earamount = earamount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
