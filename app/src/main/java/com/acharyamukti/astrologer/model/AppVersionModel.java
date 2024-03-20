package com.acharyamukti.astrologer.model;

public class AppVersionModel {
    String user_app_version;
    String astrologer_app_version;


    public AppVersionModel(String user_app_version, String astrologer_app_version) {
        this.astrologer_app_version = astrologer_app_version;
        this.user_app_version = user_app_version;
    }

    public String getUser_app_version() {
        return user_app_version;
    }

    public String getAstrologer_app_version() {
        return astrologer_app_version;
    }

    public void setUser_app_version(String user_app_version) {
        this.user_app_version = user_app_version;
    }

    public void setAstrologer_app_version(String astrologer_app_version) {
        this.astrologer_app_version = astrologer_app_version;
    }
}
