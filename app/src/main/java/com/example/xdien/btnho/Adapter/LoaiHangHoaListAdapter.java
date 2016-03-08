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

import com.example.xdien.btnho.Objects.HangHoa;
import com.example.xdien.btnho.Objects.LoaiHangHang;
import com.example.xdien.btnho.R;
import com.example.xdien.btnho.ThemActivity;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by xdien on 3/7/16.
 */
public class LoaiHangHoaListAdapter extends BaseAdapter {
    Context context;
    ArrayList<LoaiHangHang> dsHangHoas;
    SharedPreferences caiDat;
    SharedPreferences.Editor caiDatEditor;
    String path;

    public LoaiHangHoaListAdapter(Context context, ArrayList<LoaiHangHang> dsHangHoas) {
        this.context = context;
        caiDat = context.getSharedPreferences(ThemActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        this.dsHangHoas = dsHangHoas;
        path = caiDat.getString(ThemActivity.KEY_PATH,
                Environment.getExternalStorageDirectory().getAbsolutePath()+"/btNho");
    }

    @Override
    public int getCount() {
        return dsHangHoas.size();
    }

    @Override
    public Object getItem(int position) {
        return dsHangHoas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dsHangHoas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HangHoaItem hangHoaItem;
        if(convertView == null){
            hangHoaItem = new HangHoaItem();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_hang_hoa,parent,false);
            hangHoaItem.imageView = (ImageView) convertView.findViewById(R.id.hinhImageView);
            hangHoaItem.tenTextView = (TextView) convertView.findViewById(R.id.tenTextView);
            hangHoaItem.syncCheckBox = (CheckBox) convertView.findViewById(R.id.syncCheckBox);
            convertView.setTag(hangHoaItem);
        }else{
            hangHoaItem = (HangHoaItem) convertView.getTag();
        }
        //load tu duong dan
        File imgFile = new File(path + "/"+ dsHangHoas.get(position).getDuongDan());
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            hangHoaItem.imageView.setImageBitmap(myBitmap);
        }
        hangHoaItem.tenTextView.setText(dsHangHoas.get(position).getTenLoaiHangHoa());
        hangHoaItem.syncCheckBox.setChecked(dsHangHoas.get(position).isSync());
        return convertView;
    }
    class HangHoaItem{
        ImageView imageView;
        TextView tenTextView;
        TextView maloaiTextView;
        TextView motaTextView;
        CheckBox syncCheckBox;

    }
}
