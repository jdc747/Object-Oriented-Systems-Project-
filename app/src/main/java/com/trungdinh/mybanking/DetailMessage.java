package com.trungdinh.mybanking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class DetailMessage extends AppCompatActivity {

    public static final String EXTRA_EMAIL = "email";
    public static final String EXTRA_TYPE = "inbox";
    public static final String EXTRA_POSITION = "position";

    private  String ID;

    public String getEmail(){
        return (String)getIntent().getExtras().get(EXTRA_EMAIL);
    }

    public String getType(){
        return (String)getIntent().getExtras().get(EXTRA_TYPE);
    }

    public String getPosition(){
        return (String)getIntent().getExtras().get(EXTRA_POSITION);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get list of message
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(5);
        nameValuePair.add(new BasicNameValuePair("fromEmail", getEmail()));
        nameValuePair.add(new BasicNameValuePair("at", getType()));
        nameValuePair.add(new BasicNameValuePair("id", getPosition()));
        nameValuePair.add(new BasicNameValuePair("codePost", "getDetailMessage"));

        SendToServer2 sendTo = new SendToServer2(nameValuePair);
        this.ID = sendTo.getCode();

        //sendTo.toast(this, sendTo.getCode());

        String[] result = sendTo.getCause().split(":::");

        // to email
        TextView toEmail = (TextView)findViewById(R.id.toEmail);
        toEmail.setText("Send to: "+result[0]);

        // to subject
        TextView subject = (TextView)findViewById(R.id.subject);
        subject.setText("Subject: "+result[1]);

        // to message
        TextView message = (TextView)findViewById(R.id.message);
        message.setText(sendTo.getDes());

    }

    public void onBackMessage(View view){

        Intent intent = new Intent(this,Message.class);
        intent.putExtra(Message.EXTRA_EMAIL , getEmail());
        startActivity(intent);

    }

    public void onDelete(View view){
        // get list of message
        List<NameValuePair> nameValuePair1 = new ArrayList<NameValuePair>(5);
        nameValuePair1.add(new BasicNameValuePair("ID", this.ID ));
        nameValuePair1.add(new BasicNameValuePair("at", getType()));
        nameValuePair1.add(new BasicNameValuePair("codePost", "deleteMessage"));

        SendToServer2 sendTo = new SendToServer2(nameValuePair1);
        sendTo.toast(this, sendTo.getCause());

        Intent intent = new Intent(this,Message.class);
        intent.putExtra(Message.EXTRA_EMAIL, getEmail());
        startActivity(intent);
    }

}
