package com.trungdinh.mybanking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.trungdinh.mybanking.API.ApiInterface;
import com.trungdinh.mybanking.API.ChangePassword;
import com.trungdinh.mybanking.API.RestClient;
import com.trungdinh.mybanking.Chat.ChatActivityMain;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class LoginActivity extends Activity {

    EditText email,password;
    Button Login,register;
    String emailtxt, passwordtxt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //ApiInterface
        final ApiInterface service = RestClient.getClient();

        //Edittext
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.ID);

        //Buttons
        Login = (Button)findViewById(R.id.InitDeletebtn);
        register = (Button)findViewById(R.id.register);

        //Register Onclick
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regactivity = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(regactivity);
                finish();
            }
        });

        //Login Onclick
        Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //data
                emailtxt = email.getText().toString();
                passwordtxt = password.getText().toString();

                Call<ChangePassword> call = service.CHANGE_PASSWORD_CALL(emailtxt, passwordtxt, passwordtxt);
                //Asynchronous Request
                call.enqueue(new Callback<ChangePassword>() {
                    @Override
                    public void onResponse(Response<ChangePassword> response, Retrofit retrofit) {
                        Log.d("Keys", "email = " + emailtxt);
                        Log.d("Keys", "password = " + passwordtxt);
                        if (response.isSuccess()) {
                            // request successful (status code 200)
                            email.setText("");
                            password.setText("");
                            Toast.makeText(LoginActivity.this, "Success Code = " + response.code() + ": " + response.message(), Toast.LENGTH_SHORT).show();
                            Intent JoinActivity = new Intent(LoginActivity.this, ChatActivityMain.class);
                            startActivity(JoinActivity);
                        } else {
                            //Handle errors
                            Toast.makeText(LoginActivity.this, "Failure Code = " + response.code() + ": " + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.d("LoginActivity", "failed: " + t);
                    }
                });
            }
        });
    }




}
