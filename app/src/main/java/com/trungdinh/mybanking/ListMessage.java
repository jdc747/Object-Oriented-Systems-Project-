package com.trungdinh.mybanking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class ListMessage extends AppCompatActivity {

    public static final String EXTRA_EMAIL = "email";
    public static final String EXTRA_TYPE = "inbox";

    public String getEmail(){
        return (String)getIntent().getExtras().get(EXTRA_EMAIL);
    }

    public String getType(){
        return (String)getIntent().getExtras().get(EXTRA_TYPE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> listView,
                                            View itemView,
                                            int position,
                                            long id) {

                            Intent intent = new Intent(ListMessage.this, DetailMessage.class);
                            intent.putExtra(DetailMessage.EXTRA_EMAIL, getEmail() );
                            intent.putExtra(DetailMessage.EXTRA_TYPE, getType() );
                            intent.putExtra(DetailMessage.EXTRA_POSITION, Integer.toString(position)  );
                            startActivity(intent);
                    }
                };


        // get list of message
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(5);
        nameValuePair.add(new BasicNameValuePair("fromEmail", getEmail()));
        nameValuePair.add(new BasicNameValuePair("at", getType()));
        nameValuePair.add(new BasicNameValuePair("codePost", "getList"));

        SendToServer2 sendTo = new SendToServer2(nameValuePair);
        sendTo.toast(this, sendTo.getCause());
        String[] items =  sendTo.getDes().split(":::");

        ListView lst = (ListView)findViewById(R.id.messagingList);
        //Add the listener to the list view


        lst.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
        lst.setOnItemClickListener(itemClickListener);

    }


}
