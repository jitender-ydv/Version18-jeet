package com.example.abhishek.version_18.model;

import java.util.Date;

public class User {
    private String name,clgname,contact,email,tname,gname,gphone,ggender;

    public User(String name, String clgname,String email,String contact,
                String tname,String gname,String gphone,String ggender) {
        this.name = name;
        this.clgname = clgname;
        this.contact = contact;
        this.email = email;
        this.tname = tname;
        this.gname = gname;
        this.gphone = gphone;
        this.ggender = ggender;

    }

    public String getGgender() {
        return ggender;
    }

    public String getGname() {
        return gname;
    }

    public String getGphone() {
        return gphone;
    }

    public String getTname() {
        return tname;
    }

    public String getName() {
        return name;
    }

    public String getClgname() {
        return clgname;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

}
