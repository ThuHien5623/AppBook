package com.example.appbook.model;

public class TaiKhoan {
    private int mId;
    private String mTenDangNhap;
    private String mMatKhau;
    private String mNhapLaiMatKhau;
    private String mSDT;
    private int mPhanQuyen;

    //Hàm khởi tạo
    public TaiKhoan(String mTenDangNhap, String mMatKhau, String mSDT, int mPhanQuyen) {
        this.mTenDangNhap = mTenDangNhap;
        this.mMatKhau = mMatKhau;
        this.mSDT = mSDT;
        this.mPhanQuyen = mPhanQuyen;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTenDangNhap() {
        return mTenDangNhap;
    }

    public void setmTenDangNhap(String mTenDangNhap) {
        this.mTenDangNhap = mTenDangNhap;
    }

    public String getmMatKhau() {
        return mMatKhau;
    }

    public void setmMatKhau(String mMatKhau) {
        this.mMatKhau = mMatKhau;
    }

    public String getmNhapLaiMatKhau() {
        return mNhapLaiMatKhau;
    }

    public void setmNhapLaiMatKhau(String mNhapLaiMatKhau) {
        this.mNhapLaiMatKhau = mNhapLaiMatKhau;
    }

    public String getmSDT() {
        return mSDT;
    }

    public void setmSDT(String mSDT) {
        this.mSDT = mSDT;
    }

    public int getmPhanQuyen() {
        return mPhanQuyen;
    }

    public void setmPhanQuyen(int mPhanQuyen) {
        this.mPhanQuyen = mPhanQuyen;
    }
}
