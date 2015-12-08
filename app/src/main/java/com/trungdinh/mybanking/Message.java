package com.trungdinh.mybanking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class Message extends AppCompatActivity {

    public static final String EXTRA_EMAIL = "email";

    public String getEmail(){
        return (String)getIntent().getExtras().get(EXTRA_EMAIL);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        setValueForView();

    }

    public void onCreateMessage(View view){
        Intent intent = new Intent(this,CreateMessage.class);
        intent.putExtra(CreateMessage.EXTRA_EMAIL, getEmail());
        startActivity(intent);
    }

    public void setValueForView(){
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("fromEmail", getEmail() ));
        nameValuePair.add(new BasicNameValuePair("codePost", "checkQuantity"));

        SendToServer2 sendTo = new SendToServer2(nameValuePair);
        String[] list = sendTo.getDes().split(",");

        TextView inboxView = (TextView)findViewById(R.id.inboxView);
        inboxView.setText(list[0]);

        TextView sentView = (TextView)findViewById(R.id.sentView);
        sentView.setText(list[1]);

        TextView trashView = (TextView)findViewById(R.id.trashView);
        trashView.setText(list[2]);

        //sendTo.toast(this, sendTo.getCause())
    }

    public void onInBox(View view){
        listMessage(view, "inbox");
    }

    public void onSentBox(View view){
        listMessage(view, "sent");
    }

    public void onTrashBox(View view){
        listMessage(view, "trash");
    }

    public void listMessage(View view, String type){

        Intent intent = new Intent(this,ListMessage.class);
        intent.putExtra(ListMessage.EXTRA_EMAIL, getEmail());
        intent.putExtra(ListMessage.EXTRA_TYPE, type);
        startActivity(intent);
    }
}
