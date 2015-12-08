package com.trungdinh.mybanking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class CreateMessage extends AppCompatActivity {

    public static final String EXTRA_EMAIL = "email";

    public String getEmail(){
        return (String)getIntent().getExtras().get(EXTRA_EMAIL);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView from  = (TextView)findViewById(R.id.fromEmail);
        from.setText("From: " + getEmail());
    }

    public void onSending(View view){

         // get username
        EditText toEmailView  = (EditText)findViewById(R.id.toEmail);
        String toEmailText = toEmailView.getText().toString();

        // get subject
        EditText subjectView = (EditText)findViewById(R.id.subject);
        String subjectText = subjectView.getText().toString();

        // get
        EditText messageView = (EditText)findViewById(R.id.message);
        String messageText  = messageView.getText().toString();

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(5);
        nameValuePair.add(new BasicNameValuePair("fromEmail", getEmail() ));
        nameValuePair.add(new BasicNameValuePair("toEmail", toEmailText ));
        nameValuePair.add(new BasicNameValuePair("subject", subjectText ));
        nameValuePair.add(new BasicNameValuePair("message", messageText ));
        nameValuePair.add(new BasicNameValuePair("codePost", "sending"));

        SendToServer2 sendTo = new SendToServer2(nameValuePair);
        sendTo.toast(this, sendTo.getCause());

        Intent intent = new Intent(this,Message.class);
        intent.putExtra(Message.EXTRA_EMAIL, (String) getEmail());
        startActivity(intent);

    }

}
