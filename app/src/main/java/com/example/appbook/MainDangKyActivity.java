package com.example.appbook;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.appbook.model.TaiKhoan;

public class MainDangKyActivity extends Activity {
    EditText etUsername, etPhone, etPassword, etPasswordAgain;
    Button btnCreateAccount;
    ImageView backButton;
    MyDatabaseHelper databasedoctruyen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);

        databasedoctruyen = new MyDatabaseHelper(this);
        AnhXa();

        //click cho button đăng ký
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taikhoan = etUsername.getText().toString();
                String matkhau = etPassword.getText().toString();
                String sdt = etPhone.getText().toString();
                String nhaplaimatkhau = etPasswordAgain.getText().toString();

                TaiKhoan taiKhoan1 = CreateTaiKhoan();


                if (taikhoan.equals("") || sdt.equals("") || matkhau.equals("") || nhaplaimatkhau.equals("")){
                    Log.e("Thông báo : ", "Vui lòng nhập đầy đủ thông tin!");
                    Toast.makeText(MainDangKyActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_LONG).show();
                } else if (isUsernameTaken(taikhoan)) {
                    Log.e("Thông báo : ", "Tài khoản đã tồn tại!");
                    Toast.makeText(MainDangKyActivity.this, "Tài khoản đã tồn tại!", Toast.LENGTH_LONG).show();
                }
                //Nếu đầy đủ thông tin thì add vào tài khoản database
                else {
                    if (!matkhau.equals(nhaplaimatkhau)){
                        Log.e("Thông báo : ", "Mật khẩu không khớp!");
                        Toast.makeText(MainDangKyActivity.this, "Mật khẩu không khớp!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        databasedoctruyen.AddTaiKhoan(taiKhoan1);
                        Toast.makeText(MainDangKyActivity.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();

                        // Clear input fields
                        etUsername.setText("");
                        etPassword.setText("");
                        etPhone.setText("");
                        etPasswordAgain.setText("");
                    }
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainDangKyActivity.this, MainDangNhapActivity.class);
                startActivity(intent);
            }
        });
    }

    //Phương thức tạo tài khoản
    private TaiKhoan CreateTaiKhoan() {
        String taikhoan = etUsername.getText().toString();
        String sdt = etPhone.getText().toString();
        String matkhau = etPassword.getText().toString();
        int phanquyen = 1;

        TaiKhoan tk = new TaiKhoan(taikhoan, matkhau, sdt, phanquyen);
        return tk;
    }

    private boolean isUsernameTaken(String username) {
        Cursor cursor = databasedoctruyen.getAccountByUsername(username);
        boolean exists = cursor.getCount() > 0; // If count is greater than 0, the username exists
        cursor.close();
        return exists;
    }


    public void AnhXa() {
        etUsername = findViewById(R.id.etUsername);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etPasswordAgain = findViewById(R.id.etPasswordAgain);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        backButton = findViewById(R.id.back_button);

    }
}
