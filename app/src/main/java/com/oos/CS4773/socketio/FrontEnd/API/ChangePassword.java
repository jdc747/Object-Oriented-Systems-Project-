package com.oos.CS4773.socketio.FrontEnd.API;


public class ChangePassword {

    String NewPass, OldPass, Email;
    public String getNewPass() {
        return NewPass;
    }

    public void setNewPass(String newPass) {
        NewPass = newPass;
    }

    public String getOldPass() {
        return OldPass;
    }

    public void setOldPass(String oldPass) {
        OldPass = oldPass;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }



}
