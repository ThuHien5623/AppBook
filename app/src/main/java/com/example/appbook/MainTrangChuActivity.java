package com.example.appbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;

import androidx.annotation.Nullable;

public class MainTrangChuActivity extends Activity {
    Button btn_reading, btn_favorite;
    ImageView btn_DangXuat;
    MyDatabaseHelper databasedoctruyen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu);

        // Khởi tạo đối tượng MyDatabaseHelper
        databasedoctruyen = new MyDatabaseHelper(this);

        btn_reading = findViewById(R.id.btn_reading);
        btn_favorite = findViewById(R.id.btn_favorite);
        btn_DangXuat = findViewById(R.id.btnDangXuat);

        // THE LOẠI //
        // Tìm LinearLayout từ layout
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        if (linearLayout == null) {
            throw new IllegalStateException("Không tìm thấy LinearLayout với ID 'linearLayout' trong trangchu.xml");
        }

        // Truy vấn SQLite và lấy dữ liệu
        Cursor cursor = null;
        try {
            cursor = databasedoctruyen.getAllTheLoai(); // Lấy dữ liệu từ phương thức helper
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    // Lấy chỉ số của cột "theloai_ten"
                    int columnIndex = cursor.getColumnIndex("theloai_ten");
                    if (columnIndex != -1) {
                        String tentheloai = cursor.getString(columnIndex);

                        // Tạo TextView và thêm dữ liệu
                        TextView textView = new TextView(this);
                        textView.setText(tentheloai);
                        textView.setPadding(16, 16, 16, 16);
                        textView.setBackgroundColor(Color.LTGRAY);
                        textView.setTextColor(Color.BLACK);

                        // Thiết lập LayoutParams để thêm margin
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT, // Chiều rộng
                                LinearLayout.LayoutParams.WRAP_CONTENT  // Chiều cao
                        );
                        params.setMargins(20, 20, 10, 20); // Thiết lập margin: left, top, right, bottom
                        textView.setLayoutParams(params);

                        // Thêm sự kiện OnClickListener
                        textView.setOnClickListener(v -> {
                            // Chuyển sang màn hình khác khi click
                            if (tentheloai != null) {
                                Intent intent = new Intent(MainTrangChuActivity.this, MainBookListActivity.class);
                                intent.putExtra("theloai_ten", tentheloai);  // Truyền tên thể loại
                                startActivity(intent);  // Bắt đầu Activity mới
                            } else {
                                Log.e("MainTrangChu", "Tên thể loại là null");
                            }
                        });

                        // Thêm TextView vào LinearLayout
                        linearLayout.addView(textView);
                    } else {
                        throw new IllegalArgumentException("Cột 'theloai_ten' không tồn tại trong bảng!");
                    }
                } while (cursor.moveToNext());
            } else {
                // Xử lý khi không có dữ liệu
                TextView textView = new TextView(this);
                textView.setText("Không có dữ liệu!");
                textView.setPadding(16, 16, 16, 16);
                textView.setTextColor(Color.RED);

                linearLayout.addView(textView);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Ghi log lỗi
        } finally {
            // Đảm bảo đóng Cursor
            if (cursor != null) {
                cursor.close();
            }
        }


        // NOI BAT //


        // MOI XUAT BAN //

        // LinearLayout chứa các mục sách
        LinearLayout linear_layout_books = findViewById(R.id.linear_layout_books);

        Cursor cursorMoiXuatBan = databasedoctruyen.GetTop10SachMoiXuatBan();

        if (cursorMoiXuatBan != null && cursorMoiXuatBan.moveToFirst()) {
            do {
                int columnIndexTitle = cursorMoiXuatBan.getColumnIndex("truyen_tentruyen");
                int columnIndexAuthor = cursorMoiXuatBan.getColumnIndex("truyen_tentacgia");
                int columnIndexImage = cursorMoiXuatBan.getColumnIndex("truyen_image");
                int columnIndexTheLoai = cursorMoiXuatBan.getColumnIndex("theloai_ten");
                int columnIndexMoTa = cursorMoiXuatBan.getColumnIndex("truyen_mota");

                String tentruyen;
                String tentacgia;
                String image;
                String theloai;
                String mota;


                // Nếu cột tồn tại và không phải là -1 (tức là có cột này)
                if (columnIndexTitle != -1) {
                    tentruyen = cursorMoiXuatBan.getString(columnIndexTitle);
                } else {
                    tentruyen = "";
                }
                if (columnIndexAuthor != -1) {
                    tentacgia = cursorMoiXuatBan.getString(columnIndexAuthor);
                }
                else {
                    tentacgia = "";
                }
                if (columnIndexImage != -1) {
                    image = cursorMoiXuatBan.getString(columnIndexImage);
                }
                else {
                    image = "";
                }
                if (columnIndexTheLoai != -1) {
                    theloai = cursorMoiXuatBan.getString(columnIndexTheLoai);
                }
                else {
                    theloai = "";
                }
                if (columnIndexMoTa != -1) {
                    mota = cursorMoiXuatBan.getString(columnIndexMoTa);
                }
                else {
                    mota = "";
                }


                // Tạo LinearLayout cho mỗi sách
                LinearLayout bookLayout = new LinearLayout(this);
                bookLayout.setOrientation(LinearLayout.VERTICAL);
                bookLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                bookLayout.setPadding(5, 5, 5, 5);

                // Tạo ImageView cho hình ảnh của sách
                ImageView bookImage = new ImageView(this);
                // Đảm bảo rằng đường dẫn đến hình ảnh hợp lệ
                bookImage.setImageResource(getResources().getIdentifier(image, "drawable", getPackageName()));
                bookImage.setLayoutParams(new LinearLayout.LayoutParams(400, 500));
                bookLayout.addView(bookImage);

                // Tạo TextView cho tên sách
                TextView bookTitle = new TextView(this);
                bookTitle.setText(tentruyen);
                bookTitle.setTextSize(14);
                bookTitle.setTextColor(Color.BLACK);
                bookTitle.setGravity(Gravity.CENTER);
                bookLayout.addView(bookTitle);

                // Tạo TextView cho tên tác giả
                TextView bookAuthor = new TextView(this);
                bookAuthor.setText(tentacgia);
                bookAuthor.setTextColor(Color.parseColor("#666666"));
                bookAuthor.setTextSize(12);
                bookAuthor.setGravity(Gravity.CENTER);
                bookLayout.addView(bookAuthor);

                // Thêm sự kiện click vào LinearLayout của sách
                bookLayout.setOnClickListener(v -> {
                    // Gửi dữ liệu qua Intent để chuyển sang màn hình chi tiết sách
                    Intent intent = new Intent(MainTrangChuActivity.this, MainChiTietBookActivity.class);
                    intent.putExtra("truyen_tentruyen", tentruyen);
                    intent.putExtra("truyen_tentacgia", tentacgia);
                    intent.putExtra("truyen_image", image);
                    intent.putExtra("theloai_ten", theloai);
                    intent.putExtra("truyen_mota", mota);
                    startActivity(intent);

                });

                // Thêm Layout vào LinearLayout chính
                linear_layout_books.addView(bookLayout);

            } while (cursorMoiXuatBan.moveToNext());
            cursorMoiXuatBan.close();
        }





        //Tạo sự kiện click button đang đọc khi chuyển sang màn hình đang đọc với Intent
        btn_reading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainTrangChuActivity.this, MainDangDocActivity.class);
                startActivity(intent);
            }
        });

        //Tạo sự kiện click button yêu thích khi chuyển sang màn hình yeu thich với Intent
        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainTrangChuActivity.this, MainYeuThichActivity.class);
                startActivity(intent);
            }
        });

        //Tạo sự kiện click button đăng xuất thì tro ve trang dang nhap
        btn_DangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainTrangChuActivity.this, MainDangNhapActivity.class);
                startActivity(intent);
            }
        });
    }
}
