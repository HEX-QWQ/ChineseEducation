package com.Bean;

import java.sql.Date;



public class User {
    private String account;
    private String pwd;
    private String sname;
    private String nick;
    private Date birthday;
    private int time;
    private int vn;

    public User() {

    }

    public User(String account, String pwd, String sname, String nick, Date birthday, int time, int vn) {
        this.account = account;
        this.pwd = pwd;
        this.sname = sname;
        this.nick = nick;
        this.birthday = birthday;
        this.time = time;
        this.vn = vn;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getVn() {
        return vn;
    }

    public void setVn(int vn) {
        this.vn = vn;
    }
}
