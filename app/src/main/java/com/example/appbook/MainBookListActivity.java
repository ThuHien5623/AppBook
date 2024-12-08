package com.example.appbook;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable; // Để chú thích các phương thức có thể null

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class MainBookListActivity extends Activity {
    Button btn_reading, btn_favorite, btn_trangchu;

    MyDatabaseHelper databasedoctruyen;
    List<Book> books;
    BookAdapter bookAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);

        // Nhận dữ liệu từ Intent
        String tentheloai = getIntent().getStringExtra("theloai_ten");
        // Hiển thị tên thể loại
        TextView textView = findViewById(R.id.tvTenTheLoai);
        textView.setText(tentheloai);
        btn_reading = findViewById(R.id.btn_reading);
        btn_favorite = findViewById(R.id.btn_favorite);
        btn_trangchu = findViewById(R.id.btn_home);

        //Đổ danh sách truyện
        //----------------
        // Khởi tạo ListView
        ListView listView = findViewById(R.id.listViewBooks);
        // Lấy dữ liệu từ SQLite
        databasedoctruyen = new MyDatabaseHelper(this);
        books = new ArrayList<>();
        Cursor cursor = databasedoctruyen.getBooksByCategory(tentheloai);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Lấy chỉ mục của các cột từ cursor
                int columnIndexTitle = cursor.getColumnIndex("truyen_tentruyen");
                int columnIndexAuthor = cursor.getColumnIndex("truyen_tentacgia");
                int columnIndexCover = cursor.getColumnIndex("truyen_image");

                // Kiểm tra nếu cột tồn tại và lấy giá trị
                String tensach = "";
                String tacgia = "";
                String image = "";

                // Nếu cột tồn tại và không phải là -1 (tức là có cột này)
                if (columnIndexTitle != -1) {
                    tensach = cursor.getString(columnIndexTitle);
                }
                if (columnIndexAuthor != -1) {
                    tacgia = cursor.getString(columnIndexAuthor);
                }
                if (columnIndexCover != -1) {
                    image = cursor.getString(columnIndexCover);
                }
                books.add(new Book(tensach, tacgia, image));
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        // Gắn Adapter
        bookAdapter = new BookAdapter(this, books);
        listView.setAdapter(bookAdapter);

//        // Xử lý sự kiện click
////        listView.setOnItemClickListener((parent, view, position, id) -> {
////            Book selectedBook = books.get(position);
////
////            Intent intent = new Intent(MainBookListActivity.this, BookDetailActivity.class);
////            intent.putExtra("title", selectedBook.getTitle());
////            intent.putExtra("author", selectedBook.getAuthor());
////            intent.putExtra("cover", selectedBook.getCover());
////            startActivity(intent);
////        });




        //-----------------

        //Tạo sự kiện click button đang đọc khi chuyển sang màn hình đang đọc với Intent
        btn_reading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainBookListActivity.this, MainDangDocActivity.class);
                startActivity(intent);
            }
        });

        //Tạo sự kiện click button yêu thích khi chuyển sang màn hình yeu thich với Intent
        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainBookListActivity.this, MainYeuThichActivity.class);
                startActivity(intent);
            }
        });

        //Tạo sự kiện click button trang chủ khi chuyển sang màn hình trang chủ với Intent
        btn_trangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainBookListActivity.this, MainTrangChuActivity.class);
                startActivity(intent);
            }
        });

    }
}
