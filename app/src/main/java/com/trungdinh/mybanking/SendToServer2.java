package com.trungdinh.mybanking;

import android.content.Context;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by trungdin 12/6/15.
 */
public class SendToServer2 {
    private String code;
    private String cause;
    private String des;


    public String getCode(){
        return this.code;
    }

    public String getCause(){
        return this.cause;
    }

    public String getDes(){
        return this.des;
    }

    public void toast(Context obj, String str )
    {
        CharSequence text = str ;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(obj, text, duration);
        toast.show();
    }

    SendToServer2(List<NameValuePair> nameValuePair) {


        HttpClient httpClient = new DefaultHttpClient();
        // replace with your url
        HttpPost httpPost = new HttpPost("http://vietsa.org/test");

        //Post Data
        //nameValuePair


        //Encoding POST data
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // log exception
            e.printStackTrace();
        }

        //making POST request.
        try {
            HttpResponse response = httpClient.execute(httpPost);
            // write response to log
            //Log.d("Http Post Response:",.toString());

            String result = EntityUtils.toString(response.getEntity());
            //print the result as a string , it should show the entire json object
            //Log.d("whole json", "this is the whole json object result recieved in a string:  " + result);

            JSONObject ja = new JSONObject(result);
            //get second object
            JSONObject ja2 = ja.getJSONObject("meta");
            this.code = ja2.getString("code");
            this.cause = ja2.getString("cause");
            this.des = ja2.getString("description");
            //Log.d("whole json", metaCode);

        } catch (ClientProtocolException e) {
            // Log exception
            e.printStackTrace();
        } catch (IOException e) {
            // Log exception
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
