package com.example.appbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import androidx.annotation.Nullable;

public class MainTrangChuActivity extends Activity {
    Button btn_reading, btn_favorite;

    MyDatabaseHelper databasedoctruyen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu);

        // Khởi tạo đối tượng MyDatabaseHelper
        databasedoctruyen = new MyDatabaseHelper(this);

        btn_reading = findViewById(R.id.btn_reading);
        btn_favorite = findViewById(R.id.btn_favorite);

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
//                            // Chuyển sang màn hình khác khi click
//                            Intent intent = new Intent(MainTrangChuActivity.this, MainBookListActivity.class);
//                            intent.putExtra("theloai_ten", tentheloai); // Truyền dữ liệu qua Intent
//                            startActivity(intent); // Bắt đầu Activity mới
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
    }
}
