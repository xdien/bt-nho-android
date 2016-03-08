package com.example.xdien.btnho.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.xdien.btnho.Objects.HangHoa;

import java.util.ArrayList;

/**
 * Created by xdien on 3/4/16.
 */
public class HangHoaSqlHelper extends SQLiteOpenHelper {
    static String TABLE_NAME="hanghoa";
    public static String DB_NAME="qlhh";
    static String HH_ID="id";
    static String HH_HINH="hinh";
    static String HH_MALOAI="maloai";
    static String HH_TEN="ten";
    static String HH_SYNC="sync";
    static String HH_MOTA="mota";

    SQLiteDatabase db;
    public HangHoaSqlHelper(Context context) {
        super(context, DB_NAME, null, 7);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreat = "CREATE  TABLE "+TABLE_NAME+" ("+HH_ID+" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL," +
                HH_MALOAI+" INTEGER, "+HH_TEN+" VARCHAR," + HH_HINH +" TEXT, "+HH_SYNC+" BOOLEAN, "+HH_MOTA+" TEXT)";
        db.execSQL(sqlCreat);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }
    //ham lay danh sach hang hoa khong co kem loai hang hoa
    public ArrayList<HangHoa> dsHangHoas(){
        ArrayList<HangHoa> dsmHangHoas;
        dsmHangHoas = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery("select "+HH_ID+","+HH_MALOAI+","+HH_TEN+"," +
                ""+HH_HINH+","+HH_SYNC+","+HH_MOTA+" from "+TABLE_NAME,null);
        while (cursor.moveToNext()){
            dsmHangHoas.add(new HangHoa(cursor.getLong(0), cursor.getLong(1), cursor.getString(2),
                    cursor.getString(3),cursor.getInt(4)>0,cursor.getString(5)));
        }
        return  dsmHangHoas;
    }
    //ham them hang hoa moi
    public  void themHangHoa(String ten, String hinh){
        ContentValues values = new ContentValues();
        values.put(HH_TEN,ten);
        values.put(HH_HINH,hinh);
        values.put(HH_SYNC,false);
        db.insert(TABLE_NAME, null, values);
    }
    public void themHangHoa(String ten, String hinh, String mota){
        ContentValues values = new ContentValues();
        values.put(HH_TEN,ten);
        values.put(HH_HINH,hinh);
        values.put(HH_MOTA,mota);
        values.put(HH_SYNC, false);
        db.insert(TABLE_NAME, null, values);
    }
    public  int xoa(long id){
        return  db.delete(TABLE_NAME,HH_ID + "=" + id, null);
    }
    public void update(HangHoa hangHoa){
        ContentValues values = new ContentValues();
        values.put(HH_TEN, hangHoa.getTen());
        values.put(HH_HINH, hangHoa.getHinhDuongDan());
        values.put(HH_MALOAI, hangHoa.getMaLoai());
        values.put(HH_SYNC, hangHoa.isSync());
        db.update(TABLE_NAME, values, HH_ID +"="+hangHoa.getId()+"",null);
    }
    public ArrayList<HangHoa> dsHangHoaChuaDongBo(){
        ArrayList<HangHoa> dsmHangHoas;
        dsmHangHoas = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery("select "+HH_ID+","+HH_MALOAI+","+HH_TEN+"," +
                ""+HH_HINH+","+HH_SYNC+","+HH_MOTA+" from "+TABLE_NAME+" where "+HH_SYNC+" = false",null);
        while (cursor.moveToNext()){
            dsmHangHoas.add(new HangHoa(cursor.getLong(0), cursor.getLong(1), cursor.getString(2),
                    cursor.getString(3),cursor.getInt(4)>0,cursor.getString(5)));
        }
        return  dsmHangHoas;
    }

}
