package com.example.cms;

public class reviewuser {
    String user_Name;
    String r_details;


    public reviewuser(String user_Name,String r_details) {
        this.user_Name=user_Name;
        this.r_details = r_details;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getR_details() {
        return r_details;
    }

    public void setR_details(String r_details) {
        this.r_details = r_details;
    }
}