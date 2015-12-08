package com.trungdinh.mybanking;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class banking extends AppCompatActivity {

    //private String answer="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banking);
        /*f (savedInstanceState != null) {
            answer = savedInstanceState.getString("answer");
        }*/

        CharSequence text = "Welcome to this app";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this,text, duration );
        toast.show();


        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here
        }

    }


    public void onRegister(View view){

        Intent intent = new Intent(this,Register.class);
        startActivity(intent);

    }

    public void onLogin(View view){


        // get username
        EditText userView  = (EditText)findViewById(R.id.userText);
        String userText = userView.getText().toString();

        // get
        EditText passView   = (EditText)findViewById(R.id.passText);
        String passText  = passView.getText().toString();

        TextView userError = (TextView)findViewById(R.id.userError);
        TextView passError = (TextView)findViewById(R.id.passError);


        boolean check = true;

        if( userText.length() < 2 ){
            userError.setText("Error");
            check = false;
        }else{
            userError.setText("");
        }

        if( passText.length() < 2 ){
            passError.setText("Error");
            check = false;
        }else{
            passError.setText("");
        }

        if(check){
            String url = "http://159.203.136.85/BankingSystem/login?";

            url += "email="+userText+"&password="+passText;

            //url = "http://159.203.136.85/BankingSystem/login?email=trungdinh.info@gmail.com&password=FptltqNpn";
            //userText = "trungdinh.info@gmail.com";

            SendToServer http = new SendToServer( url );
            http.toast(this, http.getNotice());

            if(!http.getCheck()){
                http.toast(this, http.getNotice());
            }else{
                Intent intent = new Intent(this,MainAccess.class);
                intent.putExtra(MainAccess.EXTRA_EMAIL , (String) userText);
                intent.putExtra(MainAccess.EXTRA_PASSWORD , (String) passText);
                startActivity(intent);
            }
        }



    }

    public void onForgotPassword(View view)
    {
        Intent intent = new Intent(this,ForgetPassword.class);
        startActivity(intent);
    }

    public void onSending(View view) {

        // get username
        EditText userView  = (EditText)findViewById(R.id.userText);
        String userText = userView.getText().toString();

        // get
        EditText passView   = (EditText)findViewById(R.id.passText);
        String passText  = passView.getText().toString();

        TextView notice = (TextView)findViewById(R.id.notice);

        StringBuilder answer = new StringBuilder();

        answer.append("UserName: ").append(userText).append('\n').append("Password: ").append(passText);

        notice.setText(answer);

    }

    public void onPolicy(View view){
        Intent intent = new Intent(this,policy.class);
        startActivity(intent);
    }

}
