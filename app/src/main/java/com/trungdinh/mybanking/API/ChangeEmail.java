package com.trungdinh.mybanking.API;

public class ChangeEmail {
    public String getOldEmail() {
        return OldEmail;
    }

    public void setOldEmail(String oldEmail) {
        OldEmail = oldEmail;
    }

    public String getNewEmail() {
        return NewEmail;
    }

    public void setNewEmail(String newEmail) {
        NewEmail = newEmail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    String OldEmail, NewEmail, Password;
}
