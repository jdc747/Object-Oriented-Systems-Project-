package com.trungdinh.mybanking;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by trungdinh82 on 12/7/15.
 */
public class Helper {
    private boolean checkTrue = false;
    private String error = null;

    public String getError(){
        return this.error;
    }

    public boolean getCheck(){
        return this.checkTrue;
    }

    public void showToast(Context obj, String str){
        CharSequence text = str ;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(obj, text, duration);
        toast.show();
    }

    public void checkPassword(String new_pass){
        String upper="QWERTYUIOPASDFGHJKLZXCVBNM";
        String spercial = "~!@#$%^&*";
        String numberal = "1234567890";

        boolean check_upper = false;
        boolean check_special  = false;
        boolean check_length = false;
        boolean check_number = false;
        boolean check_space = false;

        // length of password
        int n = new_pass.length();

        if( n > 7 ){
            check_length = true;
        }else{
            check_length = false;
        }

        for(int k=0; k < n; k++){
            if( new_pass.charAt(k)==' ') {
                check_space = true;
                break;
            }

        }


        int m = upper.length();

        for(int k=0; k < n; k++){
            for(int i=0; i < m; i++){
                if( new_pass.charAt(k)==upper.charAt(i)) {
                    check_upper = true;
                    break;
                }
            }
            if(check_upper)
                break;
        }

        m = spercial.length();

        for(int k=0; k < n; k++){
            for(int i=0; i < m; i++){
                if( new_pass.charAt(k)==spercial.charAt(i)) {
                    check_special = true;
                    break;
                }
            }
            if(check_special)
                break;
        }

        m = numberal.length();

        for(int k=0; k < n; k++){
            for(int i=0; i < m; i++){
                if( new_pass.charAt(k)==numberal.charAt(i)) {
                    check_number = true;
                    break;
                }
            }
            if(check_number)
                break;
        }

        if(!check_length){
            this.error = "Length of password should contain at least 8 characters";
        }else if(check_space) {
            this.error = "Password should not contain a space";
        }else if(!check_upper){
            this.error = "Password should contain at least 1 upper case letter";
        }else if(!check_special){
            this.error = "Password should contain at least 1 special character";
        }else if(!check_number) {
            this.error = "Password should contain at least 1 digit";
        }else{
            this.checkTrue = true;
        }
    }

}
