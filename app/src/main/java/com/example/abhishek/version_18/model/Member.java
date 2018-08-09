package com.example.abhishek.version_18.model;

public class Member {
    String member_name,member_phone,member_gender;

    public Member(String member_name, String member_phone, String member_gender){
        this.member_name = member_name;
        this.member_phone = member_phone;
        this.member_gender = member_gender;
    }

    public String getMember_gender() {
        return member_gender;
    }

    public String getMember_name() {
        return member_name;
    }

    public String getMember_phone() {
        return member_phone;
    }

    public void setMember_gender(String member_gender) {
        this.member_gender = member_gender;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public void setMember_phone(String member_phone) {
        this.member_phone = member_phone;
    }
}
