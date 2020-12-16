package com.company.user_parser;

public class User {
    private String nickname;
    private String username;
    private String password;


    public User(String nickname, String username, String password) {
        this.nickname = nickname;
        this.username = username;
        this.password = password;

    }

    @Override
    public String toString() {
        return "Nickname: "+ nickname +
                ", Username: "+ username+
                ", Password: "+ password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
