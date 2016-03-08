package com.example.xdien.btnho.CongCu;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by xdien on 3/8/16.
 */
public class HttpConection extends AsyncTask<String, String, JSONArray> {
    private String method;
    public static final String M_DELETE = "delete";
    public static final String M_POST = "post";
    public static final String M_PUT = "put";

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    protected JSONArray doInBackground(String... params) {
        HttpURLConnection connection ;
        BufferedReader reader;
        JSONArray root = null;
        URL hangHoaUrl = null;
        try {
            hangHoaUrl = new URL("http://qlmb-dreamcloud.rhcloud.com/api/HangHoas");
            connection = (HttpURLConnection)hangHoaUrl.openConnection();
            connection.setRequestMethod(method);
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer(2000);
            String line = "";
            while ((line = reader.readLine()) != null){
                buffer.append(line);
            }
            JSONTokener tokener = new JSONTokener(buffer.toString());
            root = new JSONArray(tokener);
            Log.w("s", root.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return root;
    }
}
