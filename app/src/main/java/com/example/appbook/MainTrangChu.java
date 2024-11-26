package com.example.appbook;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class MainTrangChu extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) { // Đổi Oncreate thành onCreate
        super.onCreate(savedInstanceState); // Đổi saveInstanceState thành savedInstanceState
        setContentView(R.layout.book_list);
    }
}



//public class MainDangKy extends Activity {
//    EditText etUsername, etPhone, etPassword, etPasswordAgain;
//    Button btnCreateAccount;
//    ImageView backButton;
//    MyDatabaseHelper databasedoctruyen;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.dangky);

