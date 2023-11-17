package com.example.cms;

import java.util.Date;

public class user {
     String user_Id;
     String c_details;
     Date c_Date;

    // String username;

    public user(String user_Id,String c_details,Date c_Date) {
        this.user_Id = user_Id;
        this.c_details= c_details;
        this.c_Date = c_Date;
      //  username=LoginController.user_name;
    }

    public String getC_details() {
        return c_details;
    }

    public void setC_details(String c_details) {
        this.c_details = c_details;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public Date getC_Date() {
        return c_Date;
    }

    public void setC_Date(Date c_Date) {
        this.c_Date = c_Date;
    }
}
