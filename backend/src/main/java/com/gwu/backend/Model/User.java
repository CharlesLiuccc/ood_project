package com.gwu.backend.Model;

public class User {
    private int user_id;
    private String user_mail;
    private String user_name;
    private String user_pwd;
    private int user_state;
    private int user_risk;

    public User(){
        this.user_id=-1;
        this.user_mail="";
        this.user_name="";
        this.user_pwd="";
        this.user_state=0;
        this.user_risk=0;
    }

    public User(String mail,String name,String pwd){
        this.user_id=-1;
        this.user_mail=mail;
        this.user_name=name;
        this.user_pwd=pwd;
        this.user_state=0;
        this.user_risk=0;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_risk() {
        return user_risk;
    }

    public void setUser_risk(int user_risk) {
        this.user_risk = user_risk;
    }

    public int getUser_state() {
        return user_state;
    }

    public void setUser_state(int user_state) {
        this.user_state = user_state;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }
}
