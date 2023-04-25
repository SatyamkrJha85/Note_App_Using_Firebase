package com.example.my_note;

public class user_info {
    String email;
    String Password;
    String RePassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRePassword() {
        return RePassword;
    }

    public void setRePassword(String rePassword) {
        RePassword = rePassword;
    }

    public user_info(String email, String password, String confirmpassword) {
        this.email = email;
        Password = password;
    }
}
