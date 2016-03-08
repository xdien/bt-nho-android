package com.example.xdien.btnho.Objects;

/**
 * Created by xdien on 3/4/16.
 */
public class LoaiHangHang {
    long id;
    String tenLoaiHangHoa;
    String ghiChu, duongDan;
    boolean sync = false;

    public boolean isSync() {
        return sync;
    }

    public String getDuongDan() {
        return duongDan;
    }

    public void setDuongDan(String duongDan) {
        this.duongDan = duongDan;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public LoaiHangHang(String tenLoaiHangHoa, String ghiChu) {
        this.tenLoaiHangHoa = tenLoaiHangHoa;
        this.ghiChu = ghiChu;
    }

    public LoaiHangHang(long id, String tenLoaiHangHoa, String ghiChu, boolean sync, String duongDan) {
        this.ghiChu = ghiChu;
        this.tenLoaiHangHoa = tenLoaiHangHoa;
        this.id = id;
        this.sync = sync;
        this.duongDan = duongDan;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTenLoaiHangHoa() {
        return tenLoaiHangHoa;
    }

    public void setTenLoaiHangHoa(String tenLoaiHangHoa) {
        this.tenLoaiHangHoa = tenLoaiHangHoa;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
