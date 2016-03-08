package com.example.xdien.btnho.Objects;

/**
 * Created by xdien on 3/4/16.
 */
public class HangHoa {
    long id;
    String ten;
    long maLoai;
    String hinhDuongDan;
    String mota;

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    boolean sync = false;

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public HangHoa(long id, long maLoai, String ten,  String hinhDuongDan, boolean sync, String mota) {
        this.id = id;
        this.ten = ten;
        this.maLoai = maLoai;
        this.hinhDuongDan = hinhDuongDan;
        this.sync = sync;
        this.mota = mota;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public long getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(long maLoai) {
        this.maLoai = maLoai;
    }

    public String getHinhDuongDan() {
        return hinhDuongDan;
    }

    public void setHinhDuongDan(String hinhDuongDan) {
        this.hinhDuongDan = hinhDuongDan;
    }
}
