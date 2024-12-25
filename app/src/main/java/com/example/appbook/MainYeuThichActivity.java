package com.example.appbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.List;

public class MainYeuThichActivity extends Activity {
    Button btn_reading, btn_favorite, btn_trangchu;
    MyDatabaseHelper databasedoctruyen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yeuthich);

        // Khởi tạo các View
        btn_reading = findViewById(R.id.btn_reading);
        btn_favorite = findViewById(R.id.btn_favorite);
        btn_trangchu = findViewById(R.id.btn_home);


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

    }
}
