package com.example.xdien.btnho.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xdien.btnho.Objects.LoaiHangHang;
import com.example.xdien.btnho.R;
import com.example.xdien.btnho.ThemActivity;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by xdien on 3/4/16.
 */
public class LoaiHangHoaAdapter extends BaseAdapter {
    Context context;
    ArrayList<LoaiHangHang> dsLoaiHangHangs;
    SharedPreferences caiDat;
    SharedPreferences.Editor caiDatEditor;
    String path;

    public LoaiHangHoaAdapter(Context context, ArrayList<LoaiHangHang> dsLoaiHangHangs) {
        this.context = context;
        this.dsLoaiHangHangs = dsLoaiHangHangs;
        caiDat = context.getSharedPreferences(ThemActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        this.dsLoaiHangHangs = dsLoaiHangHangs;
        path = caiDat.getString(ThemActivity.KEY_PATH,
                Environment.getExternalStorageDirectory().getAbsolutePath()+"/btNho");
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LoaihangHoaItem hangHoaItem;
        if(convertView == null){
            hangHoaItem = new LoaihangHoaItem();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_hang_hoa,parent,false);
            hangHoaItem.iconImageView = (ImageView) convertView.findViewById(R.id.hinhImageView);
            hangHoaItem.loaihhTextView = (TextView) convertView.findViewById(R.id.tenTextView);
            convertView.setTag(hangHoaItem);
        }else{
            hangHoaItem = (LoaihangHoaItem) convertView.getTag();
        }
        //load tu duong dan
        File imgFile = new File(path + "/"+ dsLoaiHangHangs.get(position).getDuongDan());
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            hangHoaItem.iconImageView.setImageBitmap(myBitmap);
        }
        hangHoaItem.loaihhTextView.setText(dsLoaiHangHangs.get(position).getTenLoaiHangHoa());
        return convertView;
    }
    class  LoaihangHoaItem{
        ImageView iconImageView;
        TextView loaihhTextView;
    }
}
