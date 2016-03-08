package com.example.xdien.btnho.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.xdien.btnho.Objects.LoaiHangHang;

import java.util.ArrayList;

/**
 * Created by xdien on 3/4/16.
 */
public class LoaiHangHoaSQLHelper extends SQLiteOpenHelper {
    static String TABLE_NAME="loaihhsss";
    static String LHH_ID="id";
    static String LHH_TEN="ten";
    static String LHH_GHICHU="maloai";
    static String LHH_HINHPATH="duongdan";
    static String LHH_SYNC="sync";

    public LoaiHangHoaSQLHelper(Context context) {
        super(context, HangHoaSqlHelper.DB_NAME, null, 7);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table "+TABLE_NAME+"("+LHH_ID+" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL, " +
                LHH_TEN+ " varchar, "+LHH_GHICHU+" text, "+LHH_HINHPATH+" text, "+LHH_SYNC+" BOOLEAN)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ TABLE_NAME);
        onCreate(db);
    }

    //Ham liet ke danh sach loaiHangHoa
    public ArrayList<LoaiHangHang> dsLoaiHangHangs(){
        ArrayList<LoaiHangHang> dsmLoaiHangHangs;
        dsmLoaiHangHangs = new ArrayList<LoaiHangHang>();
        Cursor cursor = getReadableDatabase().rawQuery("select "+LHH_ID+","+LHH_TEN+","+LHH_GHICHU+"," +
                ""+LHH_SYNC+","+LHH_HINHPATH+" " +
                "from "+TABLE_NAME, null);
        while (cursor.moveToNext()){
            dsmLoaiHangHangs.add(new LoaiHangHang(cursor.getLong(0), cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3)>0, cursor.getString(4)));
        }
        return  dsmLoaiHangHangs;
    }
    public void themLoaiHangHoa(LoaiHangHang loaiHangHang){
        ContentValues values = new ContentValues();
        values.put(LHH_TEN,loaiHangHang.getTenLoaiHangHoa());
        values.put(LHH_SYNC,loaiHangHang.isSync());
        values.put(LHH_GHICHU,loaiHangHang.getGhiChu());
        values.put(LHH_HINHPATH,loaiHangHang.getDuongDan());
        getWritableDatabase().insert(TABLE_NAME,null,values);
    }
}