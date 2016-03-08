package com.example.xdien.btnho;


import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.xdien.btnho.Objects.HangHoa;
import com.example.xdien.btnho.SQL.HangHoaSqlHelper;

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
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SyncFragment extends Fragment {
    public Button dongBoButton;
    HangHoaSqlHelper hangHoaSqlHelper;

    public SyncFragment() {
        // Required empty public constructor
        hangHoaSqlHelper = new HangHoaSqlHelper(getContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //http://stackoverflow.com/questions/22395417/error-strictmodeandroidblockguardpolicy-onnetwork\
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sync, container, false);
        dongBoButton = (Button) view.findViewById(R.id.dongBoButton);
        dongBoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpURLConnection connection ;
                BufferedReader reader;
                URL hangHoaUrl = null;
                try {
                    hangHoaUrl = new URL("http://qlmb-dreamcloud.rhcloud.com/api/HangHoas");
                    connection = (HttpURLConnection)hangHoaUrl.openConnection();
                       connection.connect();
                    InputStream stream = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuffer buffer = new StringBuffer(2000);
                    String line = "";
                    while ((line = reader.readLine()) != null){
                        buffer.append(line);
                    }
                    JSONTokener tokener = new JSONTokener(buffer.toString());
                    JSONArray root = new JSONArray(tokener);
                    Log.w("s",root.toString());
                    //lay  danh sach chua dong bo
                    ArrayList<HangHoa> dschuaDongBo = hangHoaSqlHelper.dsHangHoaChuaDongBo();
                    for(int i = 0; i < dschuaDongBo.size();i++ ){
                        
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
        return view;
    }

}
