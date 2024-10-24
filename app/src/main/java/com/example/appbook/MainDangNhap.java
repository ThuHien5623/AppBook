package com.example.appbook;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MainDangNhap extends Activity {
    EditText etUsername, etPassword;
    Button btnLogin, btnCreateAccount;

    // để tạo đối tượng cho database
    MyDatabaseHelper databasedoctruyen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);

        AnhXa();

        databasedoctruyen = new MyDatabaseHelper(this);
        databasedoctruyen.open();
        //đối tượng database

        //Tạo sự kiện click button khi chuyển sang màn hình đăng ký với Intent
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainDangNhap.this, MainDangKy.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gán cho các biến là giá trị nhập vào editText
                String tentaikhoan = etUsername.getText().toString();
                String matkhau = etPassword.getText().toString();

                //Sử dụng con trỏ để lấy dữ liệu, gọi tới getData() để lấy tất cả tài khoản ở database
                Cursor cursor = databasedoctruyen.getData();

                if (tentaikhoan.equals("") || matkhau.equals("")) {
                    Log.e("Thông báo : ", "Vui lòng nhập đầy đủ thông tin!");
                    Toast.makeText(MainDangNhap.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_LONG).show();
                } else {
                    //Thực hiện vòng lặp để lấy dữ liệu từ cursor với moveToNext() di chuyển tiếp
                    while (cursor.moveToNext()) {
                        //Lấy dữ liệu và gán vào biến, dữ liệu tài khoản ở ô 1 và mật khẩu ở ô 2, ô 0 là idtaikhoan, 3 là số điện thoại, 4 là phân quyền
                        String datatentaikhoan = cursor.getString(1);
                        String datamatkhau = cursor.getString(2);

                        if (datatentaikhoan.equals(tentaikhoan) && datamatkhau.equals(matkhau)) {
                            int idtaikhoan = cursor.getInt(0);
                            String sodienthoai = cursor.getString(3);
                            int phanquyen = cursor.getInt(4);
                            String tenTK = cursor.getString(1);

                            //Chuyển quan màn hình MainActivity
                            Intent intent = new Intent(MainDangNhap.this, MainActivity.class);

                            //Gửi dữ liệu qua Activity là MainActivity
                            intent.putExtra("idtaikhoan", idtaikhoan);
                            intent.putExtra("sodienthoai", sodienthoai);
                            intent.putExtra("phanquyen", phanquyen);
                            intent.putExtra("tenTK", tenTK);

                            startActivity(intent);
                        } else {
                            Log.e("Thông báo : ", "Tài khoản không hợp lệ!!!");
                            Toast.makeText(MainDangNhap.this, "Tài khoản không hợp lệ!!!", Toast.LENGTH_LONG).show();
                        }
                    }
                }

                //Thực hiện trả cursor về đâu
                cursor.moveToFirst();
                //đóng lại khi không dùng
//                cursor.close();
            }
        });
    }

    public void AnhXa() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
    }
}
