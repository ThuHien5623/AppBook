package com.example.appbook;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MainDangNhapActivity extends Activity {
    EditText etUsername, etPassword;
    Button btnLogin, btnCreateAccount;

    // Đối tượng cho database
    MyDatabaseHelper databasedoctruyen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Kiểm tra trạng thái đăng nhập
        SharedPreferences sharedPreferences = getSharedPreferences("AppBookPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        int idtaikhoan = sharedPreferences.getInt("idtaikhoan", -1);

        Log.d("SharedPreferences", "idtaikhoan: " + idtaikhoan);

        if (isLoggedIn && idtaikhoan != -1) {
            // Người dùng đã đăng nhập, chuyển đến MainTrangChuActivity
            Intent intent = new Intent(MainDangNhapActivity.this, MainTrangChuActivity.class);
            intent.putExtra("idtaikhoan", idtaikhoan); // Gửi thêm idtaikhoan nếu cần
            startActivity(intent);
            finish(); // Kết thúc MainDangNhapActivity
            return; // Không thực thi code bên dưới
        }

        // Người dùng chưa đăng nhập, hiển thị màn hình đăng nhập
        setContentView(R.layout.dangnhap);

        // Ánh xạ các view
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        // Khởi tạo database
        databasedoctruyen = new MyDatabaseHelper(this);
        databasedoctruyen.open();

        // Sự kiện click chuyển sang màn hình đăng ký
        btnCreateAccount.setOnClickListener(view -> {
            Intent intent = new Intent(MainDangNhapActivity.this, MainDangKyActivity.class);
            startActivity(intent);
        });

        // Sự kiện click nút đăng nhập
        btnLogin.setOnClickListener(v -> {
            String tentaikhoan = etUsername.getText().toString().trim();
            String matkhau = etPassword.getText().toString().trim();

            if (tentaikhoan.isEmpty() || matkhau.isEmpty()) {
                Toast.makeText(MainDangNhapActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_LONG).show();
                return;
            }

            Cursor cursor = databasedoctruyen.getData();

            boolean isValidUser = false;

            while (cursor.moveToNext()) {
                String datatentaikhoan = cursor.getString(1);
                String datamatkhau = cursor.getString(2);

                if (datatentaikhoan.equals(tentaikhoan) && datamatkhau.equals(matkhau)) {
                    int id_taikhoan = cursor.getInt(0);
                    String sodienthoai = cursor.getString(3);
                    int phanquyen = cursor.getInt(4);

                    // Lưu thông tin đăng nhập vào SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("idtaikhoan", id_taikhoan);
                    editor.putBoolean("isLoggedIn", true); // Lưu trạng thái đăng nhập
                    editor.apply(); // Ghi dữ liệu vào SharedPreferences


                    // Chuyển sang màn hình MainTrangChu
                    Intent intent = new Intent(MainDangNhapActivity.this, MainTrangChuActivity.class);
                    intent.putExtra("idtaikhoan", id_taikhoan);
                    intent.putExtra("sodienthoai", sodienthoai);
                    intent.putExtra("phanquyen", phanquyen);
                    startActivity(intent);
                    finish();

                    isValidUser = true;
                    break;
                }
            }

            cursor.close();

            if (!isValidUser) {
                Toast.makeText(MainDangNhapActivity.this, "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
