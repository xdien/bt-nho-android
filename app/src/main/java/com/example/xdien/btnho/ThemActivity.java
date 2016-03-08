package com.example.xdien.btnho;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.xdien.btnho.Adapter.LoaiHangHoaAdapter;
import com.example.xdien.btnho.Objects.HangHoa;
import com.example.xdien.btnho.SQL.HangHoaSqlHelper;
import com.example.xdien.btnho.SQL.LoaiHangHoaSQLHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ThemActivity extends AppCompatActivity {
    EditText tenEditText,moTaEditText;
    Button themButton,huyButton;
    HangHoaSqlHelper hangHoaSqlHelper;
    ImageView hinhImageView;
    long itemid = 0;
    int hanhdong;
    public static  final int REQ_CAMERA = 11;
    //danh cho inten camera
    String timeStamp;
    File imagesFolder;
    File image;
    String currentName;
    String path;
    //sharepreference
    SharedPreferences caiDat;
    SharedPreferences.Editor caiDatEditor;
    public static final String MyPREFERENCES = "cai_dat" ;
    public static final String KEY_PATH = "path" ;
    LoaiHangHoaSQLHelper loaiHangHoaSQLHelper;
    LoaiHangHoaAdapter loaiHangHoaAdapter;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tenEditText = (EditText) findViewById(R.id.tenEditText);
        moTaEditText = (EditText) findViewById(R.id.moTaEditText);
        themButton = (Button) findViewById(R.id.themButton);
        huyButton = (Button) findViewById(R.id.huyButton);
        //lay cai dat
        caiDat = getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        caiDatEditor = caiDat.edit();
        //nhan biet them hay sua;
        Intent intent = getIntent();
        hanhdong = intent.getIntExtra("hanhdong", 0);
        if(hanhdong == MainActivity.SK_THEM) {
            Log.w("hanhdong","them");
        }
        if(hanhdong == MainActivity.SK_SUA){
            itemid = intent.getLongExtra("id",0);
            themButton.setText("SUA");
        }

        hinhImageView = (ImageView) findViewById(R.id.imageView2);
        hinhImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
        goi intent camera lay duong dan hinh
        * */

                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                //folder stuff
                String defaultPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/btNho";
                path = caiDat.getString(KEY_PATH, defaultPath);
                imagesFolder = new File(path);
                imagesFolder.mkdirs();
                image = new File(imagesFolder, "QR_" + timeStamp + ".png");
                currentName = "";
                Uri uriSavedImage = Uri.fromFile(image);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
                startActivityForResult(intent, REQ_CAMERA);
            }
        });
        LoaiHangHoaSQLHelper hangHoaSQLHelper = new LoaiHangHoaSQLHelper(getApplicationContext());
        loaiHangHoaAdapter = new LoaiHangHoaAdapter(getApplicationContext(),hangHoaSQLHelper.dsLoaiHangHangs());
        spinner = (Spinner) findViewById(R.id.loaiHHSpiner);
        spinner.setAdapter(loaiHangHoaAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        hangHoaSqlHelper = new HangHoaSqlHelper(getApplicationContext());
    }
    public void them(View view){
        /*ArrayList<HangHoa> dsHangHoas = hangHoaSqlHelper.dsHangHoas();
        Log.w("size", dsHangHoas.size()+"");*/
        if(hanhdong == MainActivity.SK_SUA)
        {
            //hangHoaSqlHelper.update(new HangHoa(itemid, ));
        }
        if(hanhdong == MainActivity.SK_THEM) {
            hangHoaSqlHelper.themHangHoa(tenEditText.getText().toString(), currentName, moTaEditText.getText().toString());
        }
    }
    public void test(View view){
        ArrayList<HangHoa> dsHangHoas = hangHoaSqlHelper.dsHangHoas();
        Log.w("size", dsHangHoas.size() + "");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == REQ_CAMERA){
                currentName = image.getName();
                File imgFile = new File(path+"/"+currentName);
                if(imgFile.exists()){
                    Log.w("themActivity 112", currentName);
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    hinhImageView.setImageBitmap(myBitmap);
                }
                //System.out.println(data.getExtras().);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.l_in,R.anim.l_out);
    }
}
