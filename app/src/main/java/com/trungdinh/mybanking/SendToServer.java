package com.trungdinh.mybanking;

import android.content.Context;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by trungdinh82 on 12/4/15.
 */
public class SendToServer {

    private String notice;
    private boolean check;



    SendToServer(String url)
    {
        postData(url);
    }




    public void toast(Context obj, String str )
    {
        CharSequence text = str ;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(obj, text, duration);
        toast.show();
    }

    public void postData(String url) {

        this.notice = "nothing";
        this.check =  false;

        //Log.d("here5", "i got here");
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();

        HttpGet httppost = new HttpGet(url);
        try {

            // ExecuteTP Post Request
            HttpResponse response = httpclient.execute(httppost);

            //notify user if the Http response is not good
            String HttpPstResponse = response.getStatusLine().toString();
            //status code of our broswer, i.e. the site we hit is an actual site
            int returnCode = response.getStatusLine().getStatusCode();
            if( returnCode != 200){
                this.notice = "Can not open the page!";
            }


            String result = EntityUtils.toString(response.getEntity());


            JSONObject json1 = new JSONObject(result);
            JSONObject json2 = json1.getJSONObject("meta");
            String metaCode = json2.getString("code");
            String cause = json2.getString("cause");

            switch(metaCode){
                case "200":
                        if(cause.equals("Member already logged in.")){
                            //this.notice = "Member already logged in.";
                        }else if(cause.equals("Logged out")) {
                            //this.notice = cause;
                        }
                        else{
                            //this.notice = "Registration complete";
                        }

                        this.notice = cause;

                        this.check = true;
                    break;

                case "400":
                    if(cause.equals("Account already exists")){

                    }else if(cause.equals("Account creation failed")){
                        //this.notice = cause;
                    }else if(cause.equals("Member already logged in.")){
                        this.check = true;
                    }

                    this.notice = cause;

                    break;

                case "401":
                    if(cause.equals("Authorization failed.")){
                        //this.notice = cause;
                    }else if(cause.equals("")){
                        //this.notice = cause;
                    }

                    this.notice = cause;
                    break;

            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }
        //Log.d("here6", "end of postData call");
    }

    public String getNotice(){
        return this.notice;
    }

    public boolean getCheck(){
        return this.check;
    }
}
