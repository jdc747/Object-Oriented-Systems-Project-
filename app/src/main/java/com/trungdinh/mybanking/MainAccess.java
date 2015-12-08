package com.trungdinh.mybanking;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
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

public class MainAccess extends AppCompatActivity {
    GPSTracker gps;
    public String emailtxt;
    public String passwordtxt;
    EditText email,password;
    final ApiInterface service = RestClient.getClient();

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
        setContentView(R.layout.activity_main_access);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void onProfile(View view)
    {
        Intent intent = new Intent(this,UserProfile.class);
        intent.putExtra(UserProfile.EXTRA_EMAIL, getEmail());
        intent.putExtra(UserProfile.EXTRA_PASSWORD, getPassword());
        startActivity(intent);
    }

    public void onMorgage(View view)
    {
        showAccountDetail(view);
    }

    public void onChecking(View view)
    {
        showAccountDetail(view);
    }

    public void onSaving(View view)
    {
        showAccountDetail(view);
    }

    public void changePassword(View view)
    {
        // get pass
        EditText pass  = (EditText)findViewById(R.id.passText);
        String new_pass = pass.getText().toString();

        Helper helper = new Helper();
        helper.checkPassword(new_pass);

        if( !helper.getCheck() ){
            helper.showToast(this, helper.getError());
        }else{
            String url = "http://159.203.136.85/BankingSystem/account/password/";//trungdinh.info@gmail.com;

            url += getEmail()+"?old_password="+getPassword()+"&new_password="+new_pass;

            SendToServer http = new SendToServer( url );

            http.toast(this, http.getNotice());

            Intent intent = new Intent(this,banking.class);
            startActivity(intent);
        }

    }

    public void showAccountDetail(View view)
    {
        Intent intent = new Intent(this, AccountDetail.class);
        startActivity(intent);
    }

    public void onLogOut(View view){

        String url = "http://159.203.136.85/BankingSystem/logout?";

        url += "email="+getEmail()+"&password="+getPassword();

        SendToServer http = new SendToServer( url );

        if(!http.getCheck()){
            http.toast(this, http.getNotice());
        }else{
            Intent intent = new Intent(this,banking.class);
            startActivity(intent);
        }
//        Intent intent = new Intent(this,banking.class);
//        startActivity(intent);
    }
    public void onChangeEmail(){
        String url = "http://159.203.136.85/BankingSystem/account/email/trungdinh.info@gmail.com?password=123&new_email=trungdinh82@gmail.com";

        url += "email="+getEmail()+"&password="+getPassword();

        SendToServer http = new SendToServer( url );

        if(!http.getCheck()){
            http.toast(this, http.getNotice());
        }else{
            Intent intent = new Intent(this,banking.class);
            startActivity(intent);
        }
    }

    public void onMessage(View view){
        Intent intent = new Intent(this,Message.class);
        intent.putExtra(Message.EXTRA_EMAIL , getEmail());
        startActivity(intent);
    }
    public void onLiveChat(View view){
        //Intent intent = new Intent(this, LoginActivity.class);
        //startActivity(intent);


            //data
            emailtxt ="mlundy756@yahoo.";
            passwordtxt = "rn0c8u23oti99kdb";

            //emailtxt = email.getText().toString();
            //passwordtxt = password.getText().toString();

            Call<ChangePassword> call = service.CHANGE_PASSWORD_CALL(emailtxt, passwordtxt, passwordtxt);
            //Asynchronous Request
            call.enqueue(new Callback<ChangePassword>() {
                @Override
                public void onResponse(Response<ChangePassword> response, Retrofit retrofit) {
                    Log.d("Keys", "email = " + emailtxt);
                    Log.d("Keys", "password = " + passwordtxt);
                    if (response.isSuccess()) {
                        // request successful (status code 200)
                        //email.setText("");
                        //password.setText("");
                        Toast.makeText(MainAccess.this, "Success Code = " + response.code() + ": " + response.message(), Toast.LENGTH_SHORT).show();
                        Intent JoinActivity = new Intent(MainAccess.this, ChatActivityMain.class);
                        startActivity(JoinActivity);
                    } else {
                        //Handle errors
                        Toast.makeText(MainAccess.this, "Failure Code = " + response.code() + ": " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.d("LoginActivity", "failed: " + t);
                }
            });

    }

    public void getGPS(View view){
        if(view.getId()==R.id.gps ){
            gps = new GPSTracker(this);

            // check if GPS enabled
            if(gps.canGetLocation()){

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();

                // \n is for new line
                Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            }else{
                // can't get location
                // GPS or Network is not enabled
                // Ask user to enable GPS/network in settings
                gps.showSettingsAlert();
            }
        }
    }

    public void getPhoneNumber(View view){
        //  auto get phone number
        TelephonyManager teleManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String number = teleManager.getLine1Number();
        Toast.makeText(this, "Phone #: ( " + number+" )", Toast.LENGTH_LONG).show();
    }

    public void getIMEI(View view){
        TelephonyManager teleManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String device = teleManager.getDeviceId();
        Toast.makeText(this, "IMEI #: " + device, Toast.LENGTH_LONG).show();
    }

    public void getIP(View view){
      WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        Toast.makeText(this, "IP address #: " + ip, Toast.LENGTH_LONG).show();
    }

    public void getEmail(View view) {
        AccountManager accountManager = AccountManager.get(this);
        Account account = getAccount(accountManager);

        String text = "Does not exist";
        if(account != null)
            text = account.name;
        Toast.makeText(this, "Email: " + text, Toast.LENGTH_LONG).show();
    }

    private static Account getAccount(AccountManager accountManager) {
        Account[] accounts = accountManager.getAccountsByType("com.google");
        Account account;
        if (accounts.length > 0) {
            account = accounts[0];
        } else {
            account = null;
        }
        return account;
    }

}
