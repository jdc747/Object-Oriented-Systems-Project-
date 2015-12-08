package com.trungdinh.mybanking;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        setPhoneNumberTo();


    }

    public void setPhoneNumberTo(){

        // default id phoneText

        EditText userView  = (EditText)findViewById(R.id.phoneText);
        String userText = userView.getText().toString();

        //  auto get phone number
        TelephonyManager teleManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String number = teleManager.getLine1Number();
        userView.setText(number);
    }

    public void onRegister(View view){

        String error = "";
        // get bankid
        EditText bankidView = (EditText)findViewById(R.id.bankidText );
        String bankidText = bankidView.getText().toString();

        if(bankidText.length()<1){
            error += "BankID is invalid -";
        }

        // get name
        EditText nameView  = (EditText)findViewById(R.id.nameText );
        String nameText  = nameView.getText().toString();
        nameText = nameText.replace(' ','+');

        if(nameText.length()<5){
            error += "Please input Full Name -";
        }

        // get ssn
        EditText ssnView = (EditText)findViewById(R.id.ssnText );
        String ssnText = ssnView.getText().toString();

        if(ssnText.length()<5){
            error += "SSN is invalid -";
        }

        // get email
        EditText emailView  = (EditText)findViewById(R.id.emailText );
        String emailText  = emailView.getText().toString();

        if(emailText.length()<5){
            error += "Email is invalid -";
        }

        // get phone
        EditText phoneView  = (EditText)findViewById(R.id.phoneText );
        String phoneText  = phoneView.getText().toString();

        if(error.length()>4){
            CharSequence text = error ;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }else{
            String url = "http://159.203.136.85/BankingSystem/account/create/";

            url += bankidText+"?ssn="+ssnText+"&name="+nameText+"&email="+emailText;

            //url = "http://159.203.136.85/BankingSystem/account/create/4?ssn=993279341&name=Shane+Butt&email=trungdinh10@hotmail.com";

            SendToServer http = new SendToServer( url );

            http.toast(this, http.getNotice());
        }



        //Intent intent = new Intent(this,RegisterSuccess.class);
        //startActivity(intent);
    }


}