package com.example.appbook;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.List;

public class MainYeuThichActivity extends Activity {
    Button btn_reading, btn_favorite, btn_trangchu;
    ImageView back_button;
    GridLayout grid_favorites;
    MyDatabaseHelper databasedoctruyen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yeuthich);

        // Khởi tạo các View
        btn_reading = findViewById(R.id.btn_reading);
        btn_favorite = findViewById(R.id.btn_favorite);
        btn_trangchu = findViewById(R.id.btn_home);
        back_button = findViewById(R.id.back_button);
        grid_favorites = findViewById(R.id.grid_favorites);

        // Khởi tạo database
        databasedoctruyen = new MyDatabaseHelper(this);


        // Lấy taikhoan_id từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("AppBookPrefs", MODE_PRIVATE);
        int idtaikhoan = sharedPreferences.getInt("idtaikhoan", -1);

        Log.d("MainYeuThichActivity", "idtaikhoan" + idtaikhoan);

        // Truy vấn dữ liệu truyện yêu thích
        Cursor cursor = databasedoctruyen.getFavoritesByTaiKhoan(idtaikhoan);

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
                bookLayout.setPadding(15, 15, 15, 15);

                // Tạo FrameLayout cho hình ảnh
                FrameLayout frameLayout = new FrameLayout(this);
                frameLayout.setLayoutParams(new LinearLayout.LayoutParams(330, 500));
                frameLayout.setBackgroundResource(R.drawable.bt_img); // Đặt nền
                frameLayout.setClipToOutline(true);

                // ImageView cho hình ảnh truyện
                ImageView bookImage = new ImageView(this);
                int imageResId = getResources().getIdentifier(truyen_image, "drawable", getPackageName());
                if (imageResId != 0) {
                    bookImage.setImageResource(imageResId);
                }
//                else {
//                    bookImage.setImageResource(R.drawable.default_image); // Hình mặc định
//                }
                bookImage.setLayoutParams(new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
                bookImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                frameLayout.addView(bookImage);

                // Thêm FrameLayout vào bookLayout
                bookLayout.addView(frameLayout);

                // TextView cho tên truyện
                TextView bookTitle = new TextView(this);
                bookTitle.setText(truyen_tentruyen);
                bookTitle.setTextColor(getResources().getColor(android.R.color.black));
                bookTitle.setTextSize(20);
                bookTitle.setTypeface(null, Typeface.BOLD);  // Make title bold
//                bookTitle.setTextStyle(TextView.BOLD);
                bookTitle.setGravity(View.TEXT_ALIGNMENT_CENTER);
                bookLayout.addView(bookTitle);

                // TextView cho tên tác giả
                TextView bookAuthor = new TextView(this);
                bookAuthor.setText(truyen_tentacgia);
                bookAuthor.setTextSize(18);
                bookAuthor.setGravity(View.TEXT_ALIGNMENT_CENTER);
                bookLayout.addView(bookAuthor);

                // Xử lý sự kiện click cho bookLayout
                bookLayout.setOnClickListener(v -> {
                    Intent intent = new Intent(MainYeuThichActivity.this, MainChiTietBookActivity.class);
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
                grid_favorites.addView(bookLayout);

            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Log.d("MainYeuThichActivity", "Không có dữ liệu yêu thích");
        }



//--------------------
        // Xử lý sự kiện click cho các button
        btn_reading.setOnClickListener(view -> {
            Intent intent = new Intent(MainYeuThichActivity.this, MainDangDocActivity.class);
            startActivity(intent);
        });

        btn_favorite.setOnClickListener(view -> {
            Intent intent = new Intent(MainYeuThichActivity.this, MainYeuThichActivity.class);
            startActivity(intent);
        });

        btn_trangchu.setOnClickListener(view -> {
            Intent intent = new Intent(MainYeuThichActivity.this, MainTrangChuActivity.class);
            startActivity(intent);
        });

        back_button.setOnClickListener(view -> {
            Intent intent = new Intent(MainYeuThichActivity.this, MainTrangChuActivity.class);
            startActivity(intent);
        });

    }
}
