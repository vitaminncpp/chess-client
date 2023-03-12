package com.akshayaap.chess.model;

public class User {
    int userid;
    String username;
    String name;
    String password;

    public User() {
    }

    public User(int userid, String username, String name, String password) {
        this.userid = userid;
        this.username = username;
        this.name = name;
        this.password = password;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "\"userid\":\"" + userid + '\"' +
                ",\"username='" + username + '\"' +
                ",\"name\":\"" + name + '\"' +
                ",\"password\":\"" + password + '\"' +
                '}';
    }
}
