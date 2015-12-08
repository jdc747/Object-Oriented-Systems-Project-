package com.trungdinh.mybanking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.trungdinh.mybanking.API.ApiInterface;
import com.trungdinh.mybanking.API.CreateAccount;
import com.trungdinh.mybanking.API.RestClient;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class RegisterActivity extends Activity {
    EditText email, Id, Name,Ssn;
    Button login,register;
    String emailtxt, Idtxt, nametxt,ssntxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final ApiInterface service = RestClient.getClient();

        //edittext
        email = (EditText)findViewById(R.id.email);
        Id = (EditText)findViewById(R.id.ID);
        Name = (EditText)findViewById(R.id.name);
        Ssn = (EditText)findViewById(R.id.ssn);

        //buttons
        register = (Button)findViewById(R.id.registerbtn);
        login = (Button)findViewById(R.id.login);

        //Login Listener
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regactivity = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(regactivity);
                finish();
            }
        });

        //register Listener
        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                emailtxt = email.getText().toString();
                Idtxt = Id.getText().toString();
                nametxt = Name.getText().toString();
                ssntxt = Ssn.getText().toString();

                Call<CreateAccount> call = service.CREATE_ACCOUNT_CALL(Idtxt, ssntxt, nametxt, emailtxt);

                //Asynchronous Request
                call.enqueue(new Callback<CreateAccount>() {
                    @Override
                    public void onResponse(Response<CreateAccount> response, Retrofit retrofit) {

                        Log.d("RegisterActivity", "Status Code = " + response.code());
                        Log.d("RegisterActivity", "Status message = " + response.message());
                        if (response.isSuccess()) {
                            // request successful (status code 200)
                            CreateAccount result = response.body();
                            Log.d("RegisterActivity", "Success Code = " + response.code());
                            Log.d("RegisterActivity", "Status message = " + response.message());
                        } else {
                            // response received but request not successful (like 400,401,403 etc)
                            //Handle errors
                            Log.d("RegisterActivity", "fail Code = " + response.code());
                            Log.d("RegisterActivity", "Status message = " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.d("RegisterActivity", "failed: "+ t);
                    }
                });
            }


        });
    }




}
