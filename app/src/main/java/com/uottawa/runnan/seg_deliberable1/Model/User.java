package com.uottawa.runnan.seg_deliberable1.Model;
import android.content.Context;
public class User {
    private String username;
    private String password;
    private String email;
    private String role;

    public User(){

    }

    public User(String username){
        this.username = username;
    }

    public User(String username,String password,String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;

    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
