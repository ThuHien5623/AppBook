package com.example.appbook;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MainDangDocActivity extends Activity {
    Button btn_reading, btn_favorite, btn_trangchu;
    ImageView back_button;
    GridLayout grid_reading;
    MyDatabaseHelper databasedoctruyen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangdoc);

        // Khởi tạo các View
        btn_reading = findViewById(R.id.btn_reading);
        btn_favorite = findViewById(R.id.btn_favorite);
        btn_trangchu = findViewById(R.id.btn_home);
        back_button = findViewById(R.id.back_button);
        grid_reading = findViewById(R.id.grid_reading);

        // Khởi tạo database
        databasedoctruyen = new MyDatabaseHelper(this);

        // Lấy taikhoan_id từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("AppBookPrefs", MODE_PRIVATE);
        int idtaikhoan = sharedPreferences.getInt("idtaikhoan", -1);

        Log.d("MainYeuThichActivity", "idtaikhoan" + idtaikhoan);

        // Truy vấn dữ liệu truyện yêu thích
        Cursor cursor = databasedoctruyen.getReadingByTaiKhoan(idtaikhoan);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Lấy dữ liệu từ Cursor
                int columnIndexTitle = cursor.getColumnIndex("truyen_tentruyen");
                int columnIndexAuthor = cursor.getColumnIndex("truyen_tentacgia");
                int columnIndexImage = cursor.getColumnIndex("truyen_image");
                int columnIndexTheLoai = cursor.getColumnIndex("theloai_ten");
                int columnIndexMoTa = cursor.getColumnIndex("truyen_mota");
                int columnIndexTruyenID = cursor.getColumnIndex("truyen_id");
                int columnIndexTheLoaiID = cursor.getColumnIndex("theloai_id");

                String truyen_tentruyen = cursor.getString(columnIndexTitle);
                String truyen_tentacgia = cursor.getString(columnIndexAuthor);
                String truyen_image = cursor.getString(columnIndexImage);
                String theloai_ten = cursor.getString(columnIndexTheLoai);
                String truyen_mota = cursor.getString(columnIndexMoTa);
                int truyen_id = cursor.getInt(columnIndexTruyenID);
                int theloai_id = cursor.getInt(columnIndexTheLoaiID);

                Log.d("MainYeuThich", "truyen_id: " + truyen_id);
                Log.d("MainYeuThich", "truyen_tentacgia: " + truyen_tentacgia);
                Log.d("MainYeuThich", "truyen_image: " + truyen_image);
                Log.d("MainYeuThich", "theloai_ten: " + theloai_ten);
                Log.d("MainYeuThich", "truyen_mota: " + truyen_mota);
                Log.d("MainYeuThich", "truyen_tentruyen: " + truyen_tentruyen);
                Log.d("MainYeuThich", "theloai_id: " + theloai_id);


                // Tạo Layout cho mỗi item
                LinearLayout bookLayout = new LinearLayout(this);
                bookLayout.setOrientation(LinearLayout.VERTICAL);
                bookLayout.setLayoutParams(new GridLayout.LayoutParams());
                bookLayout.setPadding(10, 10, 10, 10);

                // Tạo FrameLayout cho hình ảnh
                FrameLayout frameLayout = new FrameLayout(this);
                frameLayout.setLayoutParams(new LinearLayout.LayoutParams(330, 490));
                frameLayout.setBackgroundResource(R.drawable.bt_img); // Đặt nền
                frameLayout.setClipToOutline(true);


                // ImageView cho hình ảnh truyện
                ImageView bookImage = new ImageView(this);
                int imageResId = getResources().getIdentifier(truyen_image, "drawable", getPackageName());
                if (imageResId != 0) {
                    bookImage.setImageResource(imageResId);
                }

                bookImage.setLayoutParams(new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
                bookImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                frameLayout.addView(bookImage);

                // ImageView cho hình ảnh thùng rác (bin)
                ImageView binImage = new ImageView(this);
                binImage.setLayoutParams(new FrameLayout.LayoutParams(
                       120,120,
                        Gravity.END | Gravity.BOTTOM)); // Căn góc dưới bên phải
                binImage.setImageResource(R.drawable.bin); // Hình ảnh thùng rác
                binImage.setPadding(0, 0, 0, 0); // Khoảng cách bên phải và dưới
                frameLayout.addView(binImage);

                // Thêm sự kiện click vào bin
                binImage.setOnClickListener(v -> {
                    // Xác nhận xóa sách (tùy chọn)
                    new AlertDialog.Builder(this)
                            .setTitle("Xóa sách")
                            .setMessage("Bạn có chắc muốn xóa sách này khỏi danh sách Đang Đọc không?")
                            .setPositiveButton("Xóa", (dialog, which) -> {
                                // Gọi hàm xóa trong MyDatabaseHelper
                                databasedoctruyen.removeFromReading(idtaikhoan, truyen_id);

                                // Thông báo cho người dùng
                                Toast.makeText(this, "Xóa sách thành công!", Toast.LENGTH_SHORT).show();

                                // Cập nhật giao diện: Xóa item khỏi GridLayout
                                grid_reading.removeView(bookLayout);
                            })
                            .setNegativeButton("Hủy", null)
                            .show();
                });

                // Thêm FrameLayout vào bookLayout
                bookLayout.addView(frameLayout);

                // TextView cho tên truyện
                TextView bookTitle = new TextView(this);
                bookTitle.setText(truyen_tentruyen);
                bookTitle.setTextColor(getResources().getColor(android.R.color.black));
                bookTitle.setTextSize(18);
                bookTitle.setTypeface(null, Typeface.BOLD);  // Make title bold
//                bookTitle.setTextStyle(TextView.BOLD);
                bookTitle.setGravity(View.TEXT_ALIGNMENT_CENTER);
                bookLayout.addView(bookTitle);

                // TextView cho tên tác giả
                TextView bookAuthor = new TextView(this);
                bookAuthor.setText(truyen_tentacgia);
                bookAuthor.setTextSize(15);
                bookAuthor.setGravity(View.TEXT_ALIGNMENT_CENTER);
                bookLayout.addView(bookAuthor);

                // Xử lý sự kiện click cho bookLayout
                bookLayout.setOnClickListener(v -> {
                    Intent intent = new Intent(MainDangDocActivity.this, MainChiTietBookActivity.class);
                    intent.putExtra("truyen_tentruyen", truyen_tentruyen);
                    intent.putExtra("truyen_tentacgia", truyen_tentacgia);
                    intent.putExtra("truyen_image", truyen_image);
                    intent.putExtra("theloai_ten", theloai_ten);
                    intent.putExtra("truyen_mota", truyen_mota);
                    intent.putExtra("truyen_id", truyen_id);
                    intent.putExtra("theloai_id", theloai_id);

                    startActivity(intent);
                });

                // Thêm bookLayout vào GridLayout
                grid_reading.addView(bookLayout);

            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Log.d("MainYeuThichActivity", "Không có dữ liệu yêu thích");
        }



//--------------------
        // Xử lý sự kiện click cho các button
        btn_reading.setOnClickListener(view -> {
            Intent intent = new Intent(MainDangDocActivity.this, MainDangDocActivity.class);
            startActivity(intent);
        });

        btn_favorite.setOnClickListener(view -> {
            Intent intent = new Intent(MainDangDocActivity.this, MainYeuThichActivity.class);
            startActivity(intent);
        });

        btn_trangchu.setOnClickListener(view -> {
            Intent intent = new Intent(MainDangDocActivity.this, MainTrangChuActivity.class);
            startActivity(intent);
        });

        back_button.setOnClickListener(view -> {
            Intent intent = new Intent(MainDangDocActivity.this, MainTrangChuActivity.class);
            startActivity(intent);
        });

    }
}
