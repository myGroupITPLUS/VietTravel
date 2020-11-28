package com.viettravelapplication.Model;

public class User {
    private int id;
    private String username;
    private String password;
    private int quyen;

    public User(String username, String password, int quyen) {
//        this.id = id;
        this.username = username;
        this.password = password;
        this.quyen = quyen;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getQuyen() {
        return quyen;
    }

    public void setQuyen(int quyen) {
        this.quyen = quyen;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", quyen=" + quyen +
                '}';
    }
}
