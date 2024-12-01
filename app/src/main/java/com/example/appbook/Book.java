package com.example.appbook;

public class Book {
    private String tensach;
    private String tacgia;
    private String image;

    public Book(String tensach, String tacgia, String image) {
        this.tensach = tensach;
        this.tacgia = tacgia;
        this.image = image;
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
}
