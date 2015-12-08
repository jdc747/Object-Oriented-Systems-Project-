package com.trungdinh.mybanking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class UserProfile extends AppCompatActivity {


    public static final String EXTRA_EMAIL = "email";
    public static final String EXTRA_PASSWORD  = "pass";

    public String getEmail(){
        return (String)getIntent().getExtras().get(EXTRA_EMAIL);
    }

    public String getPassword(){
        return (String)getIntent().getExtras().get(EXTRA_PASSWORD);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }

    public void onSave(View view)
    {
        // get username
        EditText firstNameView = (EditText)findViewById(R.id.firstName);
        String firstNameText = firstNameView.getText().toString();

        // get last name
        EditText lastNameView  = (EditText)findViewById(R.id.lastName);
        String lastNameText = lastNameView.getText().toString();

        // get dod
        EditText dodView = (EditText)findViewById(R.id.dod);
        String dodText = dodView.getText().toString();

        // get email
        EditText emailView  = (EditText)findViewById(R.id.email);
        String emailext = lastNameView.getText().toString();

        // get postalAdd
        EditText postalAddView = (EditText)findViewById(R.id.postalAdd);
        String postalAddText = postalAddView.getText().toString();

        // get phone
        EditText phoneView  = (EditText)findViewById(R.id.phone);
        String phoneText = phoneView.getText().toString();

        // save in database
        onBack(view);
    }

    public void onBack(View view)
    {
        Intent intent = new Intent(this,MainAccess.class);
        intent.putExtra(MainAccess.EXTRA_EMAIL, getEmail());
        intent.putExtra(MainAccess.EXTRA_PASSWORD, getPassword());
        startActivity(intent);
    }

}
