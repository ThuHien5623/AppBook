package com.example.appbook;

public class Book {
    private String tensach;
    private String tacgia;
    private String image;
    private String mota;
    private int theloai_id;

    public Book(String tensach, String tacgia, String image, String mota, int theloai_id) {
        this.tensach = tensach;
        this.tacgia = tacgia;
        this.image = image;
        this.mota = mota;
        this.theloai_id = theloai_id;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getTheloai_id() {
        return theloai_id;
    }

    public void setTheloai_id(int theloai_id) {
        this.theloai_id = theloai_id;
    }
}
