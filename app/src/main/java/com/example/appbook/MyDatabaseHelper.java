package com.example.appbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.appbook.model.TaiKhoan;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "BookLibrary.db";
    public static final int DATABASE_VERSION = 1;

    //Bảng tài khoản
    public static final String TABLE_TAIKHOAN = "tbTaiKhoan";
    public static final String TAIKHOAN_ID = "taikhoan_id";
    public static final String TAIKHOAN_TENDANGNHAP = "taikhoan_tendangnhap";
    public static final String TAIKHOAN_MATKHAU = "taikhoan_matkhau";
    public static final String TAIKHOAN_SDT = "taikhoan_sdt";
    public static final String TAIKHOAN_PHANQUYEN = "taikhoan_phanquyen";

    //Bảng truyện
    public static final String TABLE_TRUYEN = "tbTruyen";
    public static final String TRUYEN_ID = "truyen_id";
    public static final String TRUYEN_TENTRUYEN = "truyen_tentruyen";
    public static final String TRUYEN_TENTACGIA = "truyen_tentacgia";
    public static final String TRUYEN_MOTA = "truyen_mota";
    public static final String TRUYEN_IMAGE = "truyen_image";
    public static final String TRUYEN_THELOAI_ID = "theloai_id";
    public static final String TRUYEN_TRANGTHAI = "truyen_trangthai";
    public static final String TRUYEN_LUOTXEM = "truyen_luotxem";
    public static final String TRUYEN_NGAYDANG = "truyen_ngaydang";
    public static final String TRUYEN_NGAYCAPNHATCUOI = "truyen_ngaycapnhatcuoi";

    //Bảng thể loại truyện
    public static final String TABLE_THELOAI = "tbTheLoai";
    public static final String THELOAI_ID = "theloai_id";
    public static final String THELOAI_TEN = "theloai_ten";

    //Bảng truyện yêu thích
    public static final String TABLE_YEUTHICH = "tbYeuThich";
    public static final String YEUTHICH_ID = "yeuthich_id";
    public static final String YEUTHICH_TAIKHOAN_ID = "taikhoan_id";
    public static final String YEUTHICH_TRUYEN_ID = "truyen_id";
    public static final String YEUTHICH_NGAYTHEM = "yeuthich_ngaythem";

    //Bảng truyện đang đọc
    public static final String TABLE_ĐANGDOC = "tbDangDoc";
    public static final String DANGDOC_ID = "dangdoc_id";
    public static final String DANGDOC_TAIKHOAN_ID = "taikhoan_id";
    public static final String DANGDOC_TRUYEN_ID = "truyen_id";
    public static final String DANGDOC_NGAYTHEM = "dangdoc_ngaythem";

    // Tạo bảng tại phương thức này
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        this.context = context;
    }

    // Tạo bảng table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbTaiKhoan = "CREATE TABLE " + TABLE_TAIKHOAN + " ( " + TAIKHOAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TAIKHOAN_TENDANGNHAP + " TEXT, " + TAIKHOAN_MATKHAU + " TEXT, " + TAIKHOAN_SDT + " TEXT, "
                + TAIKHOAN_PHANQUYEN + " INTEGER )";

        String tbTruyen = "CREATE TABLE " + TABLE_TRUYEN + " ( " + TRUYEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TRUYEN_TENTRUYEN + " TEXT, " + TRUYEN_TENTACGIA + " TEXT, " + TRUYEN_MOTA + " TEXT, "
                + TRUYEN_IMAGE + " TEXT, " + TRUYEN_THELOAI_ID + " INTEGER, " + TRUYEN_TRANGTHAI + " TEXT, " + TRUYEN_LUOTXEM + " INTEGER, "
                + TRUYEN_NGAYDANG + " DATETIME, " + TRUYEN_NGAYCAPNHATCUOI + " DATETIME )";

        String tbTheLoai = "CREATE TABLE " + TABLE_THELOAI + " ( " + THELOAI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + THELOAI_TEN + " TEXT )";

        String tbYeuThich = "CREATE TABLE " + TABLE_YEUTHICH + " ( " + YEUTHICH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + YEUTHICH_TAIKHOAN_ID + " INTEGER, " + YEUTHICH_TRUYEN_ID + " INTEGER, " + YEUTHICH_NGAYTHEM + " DATETIME )";

        String tbDangDoc = "CREATE TABLE " + TABLE_ĐANGDOC + " ( " + DANGDOC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DANGDOC_TAIKHOAN_ID + " INTEGER, " + DANGDOC_TRUYEN_ID + " INTEGER, " + DANGDOC_NGAYTHEM + " DATETIME )";


        // Thêm tên thể loại
        String theloai_tieuthuyet = "INSERT INTO tbTheLoai VAlUES (null,'Tiểu thuyết')";
        String theloai_vanhoc = "INSERT INTO tbTheLoai VAlUES (null,'Văn học')";
        String theloai_truyendangian = "INSERT INTO tbTheLoai VAlUES (null,'Truyện dân gian')";
        String theloai_giaoduc = "INSERT INTO tbTheLoai VAlUES (null,'Giáo dục')";
        String theloai_truyenngan = "INSERT INTO tbTheLoai VAlUES (null,'Truyện ngắn')";

        // Thêm tên thể loại
        String sach_matbiec = "INSERT INTO tbTruyen VAlUES (null,'Mắt biếc','Nguyễn Nhật Ánh','Mắt biếc là một tiểu thuyết lãng mạn nổi tiếng của nhà văn Nguyễn Nhật Ánh. Câu chuyện xoay quanh tình yêu đơn phương của Ngạn, một chàng trai quê, dành cho Hà Lan, cô bạn có đôi mắt biếc cuốn hút từ thuở nhỏ. Tuy nhiên, Hà Lan lại yêu một người khác và rời xa làng quê, để lại Ngạn với nỗi nhớ và tình cảm không bao giờ được đáp lại. Mắt biếc không chỉ là câu chuyện về tình yêu mà còn là nỗi tiếc nuối của những ký ức tuổi thơ, sự thay đổi của con người qua thời gian.','matbiec',1,'2024-11-20','2024-11-20')";
        String sach_nnn = "INSERT INTO tbTruyen VAlUES (null,'Mắt biếc','Nguyễn Nhật Ánh','Mắt biếc là một tiểu thuyết lãng mạn nổi tiếng của nhà văn Nguyễn Nhật Ánh. Câu chuyện xoay quanh tình yêu đơn phương của Ngạn, một chàng trai quê, dành cho Hà Lan, cô bạn có đôi mắt biếc cuốn hút từ thuở nhỏ. Tuy nhiên, Hà Lan lại yêu một người khác và rời xa làng quê, để lại Ngạn với nỗi nhớ và tình cảm không bao giờ được đáp lại. Mắt biếc không chỉ là câu chuyện về tình yêu mà còn là nỗi tiếc nuối của những ký ức tuổi thơ, sự thay đổi của con người qua thời gian.','matbiec',1,'2024-11-20','2024-11-20')";



        db.execSQL(tbTaiKhoan);
        db.execSQL(tbTruyen);
        db.execSQL(tbTheLoai);
        db.execSQL(tbYeuThich);
        db.execSQL(tbDangDoc);

        // Thêm tên thể loại vào Database
        db.execSQL(theloai_tieuthuyet);
        db.execSQL(theloai_vanhoc);
        db.execSQL(theloai_truyendangian);
        db.execSQL(theloai_giaoduc);
        db.execSQL(theloai_truyenngan);

        // Thêm tên sách vào Database
        db.execSQL(sach_matbiec);
        db.execSQL(sach_nnn);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public SQLiteDatabase open() {
        return this.getWritableDatabase();
    }

    //Phương thức lấy tất cả tài khoản
    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_TAIKHOAN, null);
        return res;
    }

    //Phương thức add tài khoản vào database
    public void AddTaiKhoan(TaiKhoan taikhoan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Thực hiện insert thông qua ContentValues
        values.put(TAIKHOAN_TENDANGNHAP, taikhoan.getmTenDangNhap());
        values.put(TAIKHOAN_MATKHAU, taikhoan.getmMatKhau());
        values.put(TAIKHOAN_SDT, taikhoan.getmSDT());
        values.put(TAIKHOAN_PHANQUYEN, taikhoan.getmPhanQuyen());

        db.insert(TABLE_TAIKHOAN, null, values);

        //Đóng lại khi không dùng
//        db.close();
        Log.e("ADD TK", "TC");
    }

    public Cursor getAccountByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM tbTaiKhoan WHERE taikhoan_tendangnhap = ?", new String[]{username});
    }

    // Phương thức lấy tất cả thể loại truyện
    public Cursor getAllTheLoai() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_THELOAI, null);
    }

    // Phương thức Lấy truyện theo tên thể loại (dùng JOIN với tbTheLoai)
    public Cursor getBooksByCategory(String categoryName) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT t.* FROM " + TABLE_TRUYEN + " t " +
                        "JOIN " + TABLE_THELOAI + " tl ON t." + TRUYEN_THELOAI_ID + " = tl." + THELOAI_ID + " " +
                        "WHERE tl." + THELOAI_TEN + " = ?",
                new String[]{categoryName}
        );
    }

//    // Phương thức Lấy truyện theo tên ID thể loại
//    public Cursor getBooksByCategory(int categoryId) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        return db.rawQuery(
//                "SELECT * FROM " + TABLE_TRUYEN + " WHERE " + TRUYEN_THELOAI_ID + " = ?",
//                new String[]{String.valueOf(categoryId)}
//        );
//    }

}
